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

    public void execute() {
        try {
            //Build reader instance
            //Read data.outputCSV. Default seperator is comma. Default quote character is double quote
            //Start reading from line number 2 (line numbers start from zero)
            String inputCSV = "data.csv";
            CSVReader reader = new CSVReader(new FileReader(inputCSV), ',', '"', 1);
            HashSet<Integer> columnsToEncrypt = new HashSet<>();
            columnsToEncrypt.add(2);
            columnsToEncrypt.add(4);

            String outputCSV = "data_encrypted.csv";
            try {
                CSVWriter writer = new CSVWriter(new FileWriter(outputCSV));
                //Integer batchSizeForWriting = 10;

                //Read CSV line by line
                String[] nextLine;

                MessageDigest messageDigest;//Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value
                try {
                    messageDigest = MessageDigest.getInstance("SHA-256");

                    while((nextLine = reader.readNext()) != null) {
                        if (nextLine != null) {
                            logger.info(Arrays.toString(nextLine));

                            for(Integer i : columnsToEncrypt) {
                                String stringToHash = nextLine[i];
                                messageDigest.update(stringToHash.getBytes());
                                String hashedString = new String(messageDigest.digest());
                                nextLine[i] = hashedString;
                            }

                            writer.writeNext(nextLine);
                            logger.info(Arrays.toString(nextLine));
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
