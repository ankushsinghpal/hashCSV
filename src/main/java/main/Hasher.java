package main;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hasher {

    private final Logger logger = LoggerFactory.getLogger(Hasher.class);
    private final String inputCSV;
    private final String outputCSV;
    private final HashSet<Integer> columnsToEncrypt;
    private final String hashingAlgo;
    private final Integer startLine;

    public Hasher(final String inputFile, final String outputFile, final HashSet<Integer> columns, final String hashAlgorithm, final Integer startingLine) {
        this.inputCSV = inputFile;
        this.outputCSV = outputFile;
        this.columnsToEncrypt = columns;
        this.hashingAlgo = hashAlgorithm;
        this.startLine = startingLine;
    }

    public void execute() {
        try {
            //Read data.outputCSV. Default seperator is comma. Default quote character is double quote
            //Start reading from line number 2 (line numbers start from zero)
            CSVReader reader = new CSVReader(new FileReader(inputCSV), ',', '"', 0);//CSVReader(Reader reader, char separator, char quotechar, int line)

            try {
                CSVWriter writer = new CSVWriter(new FileWriter(outputCSV));
                //Read CSV line by line
                String[] nextLine;

                MessageDigest messageDigest;//Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value
                try {
                    messageDigest = MessageDigest.getInstance(hashingAlgo);
                    Integer counter = 0;
                    while((nextLine = reader.readNext()) != null) {                        
                        if (nextLine != null) {
                            if (counter >= startLine) {
                                logger.info(Arrays.toString(nextLine));

                                for(Integer i : columnsToEncrypt) {
                                    String stringToHash = nextLine[i];
                                    messageDigest.update(stringToHash.getBytes());
                                    String hashedString = new String(messageDigest.digest());
                                    nextLine[i] = hashedString;
                                }

                                writer.writeNext(nextLine);
                            }
                            else {
                                logger.info("Not hashing line {}", counter);
                                writer.writeNext(nextLine);//write the line as is without encrypting
                            }
                            logger.info(Arrays.toString(nextLine));
                            counter++;
                        }
                    }

                    writer.close();
                } catch (NoSuchAlgorithmException ex) {
                    logger.error("{}, {}", ex.getCause(), ex.getMessage());
                }
            } catch (IOException ex) {
                logger.error("{}, {}", ex.getCause(), ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            logger.error("{}, {}", ex.getCause(), ex.getMessage());
        }
    }
}
