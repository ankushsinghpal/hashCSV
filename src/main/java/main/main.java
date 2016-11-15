package main;

import java.util.HashSet;

public class main {

    public static void main(String[] args) {
        final String MD5Algorithm = "MD5";
        final String SHA1Algorithm = "SHA-1";
        final String SHA256Algorithm = "SHA-256";
        PrintUsageHint();        
        
        String inputFile = "";
        String outputFile = "";
        HashSet<Integer> columnsToHash = new HashSet<>();
        String hashingAlgorithm = MD5Algorithm;//default
        Integer startLine = 1;//default start line, to skip the first row of column names. Rows begin at 0 and so do columns.
        boolean inputsAreOK = true;
        for(int i = 0; i < args.length; ++i) {
            System.out.println("Processing commandline parameter "+i+" : "+args[i]);
            switch(i) {
                case 0:                     
                    inputFile = args[i].trim();
                break;
                case 1: 
                    outputFile = args[i].trim();
                    break;
                case 2:
                    String[] columns = args[i].split(",");
                    for(int j = 0; j < columns.length; ++j) {
                        columnsToHash.add(Integer.valueOf(columns[j].trim()));
                    }
                    break;
                case 3:
                    hashingAlgorithm = args[i].trim();
                    if (hashingAlgorithm.equals(MD5Algorithm)==false && hashingAlgorithm.equals(SHA1Algorithm)==false && hashingAlgorithm.equals(SHA256Algorithm)==false) {
                        System.out.println("Unrecognized hashing algorithm: "+hashingAlgorithm+". Exiting...");
                        inputsAreOK = false;
                    }
                    break;
                case 4:
                    startLine = Integer.valueOf(args[i].trim());
                break;
                default:
                    System.out.println("Seems like you specified some unrecognized commandline parameter: "+args[i]);
                    break;
            }
        }
        
        if (inputsAreOK) {
            Hasher hash = new Hasher(inputFile, outputFile, columnsToHash, hashingAlgorithm, startLine);
            hash.execute();
        }
    }
    
    private static void PrintUsageHint() {
        System.out.println("Usage:\n-----------------------------------------------------------------------------------------");
        System.out.println("To run this program, you need to specify the input filename, the output filename, the column numbers to hash, the hash algorithm name (optional) to use and the row to start processing the CSV (optional).");
        System.out.println("java -jar hasher.jar /path/to/file/inputFile.csv /path/to/file/outputFile.csv 2,6,10 SHA-256 \nor\n");
        System.out.println("java -jar hasher.jar /path/to/file/inputFile.csv /path/to/file/outputFile.csv 2,6,10 SHA-256 2\nor\n");
        System.out.println("java -jar hasher.jar /path/to/file/inputFile.csv /path/to/file/outputFile.csv 2,6,10");
        System.out.println("\nThe 2,6,10 means that you want to hash the second, sixth and tenth columns of inputFile.csv.");
        System.out.println("The hash algorithms you can use are: MD5 or SHA-1 or SHA-256. Specifing the algorithm is optional. MD5 will be used by default.");
        System.out.println("If you want to specify the row number to start processing, then you compulsorily have to mention the hash algorithm name.\n");
        System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n\n");
    }
    
}
