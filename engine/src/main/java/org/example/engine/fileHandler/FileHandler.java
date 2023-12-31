package org.example.engine.fileHandler;

import org.example.engine.sh1.Sha256;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileHandler {
    private static FileHandler instance = null;
    private static String path = "./.AGit/.Object/";
    private static String pathOfDbTextFile = "./.AGit/";
    private static String pathOfDB =  "C:\\Users\\asafr\\Desktop\\repositories";
    private FileHandler() { }

    public static FileHandler getInstance() {
        if (instance == null)
            instance = new FileHandler();
        return instance;
    }

    public static void writeToFile(String filePath, String... contentArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : contentArray) {
                writer.write(line);
                writer.write(","); // Add ',' between data members
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void writeToFileForCommit(String filePath, String... contentArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : contentArray) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void setPath(String p){
        path = p+ "/.AGit/.Object/";
        pathOfDbTextFile = p + "/.AGit/db.txt";
    }

    public static String getPath(){
       return  path ;
    }

    // Method to read content from a file into an array of strings
    public static String[] readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return new String[0]; // Return an empty array in case of an error
        }
    }

    public static boolean createFolder(String path) {
        File folder = new File(path);
        // Create the directory
        return folder.mkdir();
    }

    public static  ArrayList<File>  collectFileInFolder(String directoryPath) {
        // Check if the directory exists and is a directory
        ArrayList<File> filesInDirectory = new ArrayList<>();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                        filesInDirectory.add(file);
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } else {
            System.out.println("The specified directory does not exist or is not a directory.");
        }

        return filesInDirectory;

    }

    public static ArrayList<File> listFilesInFolder(String folderPath) {
        ArrayList<File> fileList = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory() ) {
            listFilesRecursively(folder, fileList);
        }
        return fileList;
    }

    private static void listFilesRecursively(File folder, List<File> fileList) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && !isInAGitFolder(file)) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    listFilesRecursively(file, fileList);
                }
            }
        }
    }

    private static boolean isInAGitFolder(File file) {
        // Check if the file's path contains the ".AGit" folder
        String agitFolder = File.separator + ".AGit" + File.separator;
        return file.getAbsolutePath().contains(agitFolder);
    }

    public static void writeToDBFile(ArrayList<String> newSh1){//write new sh1 to db file

        try{
            String filePath = pathOfDbTextFile;
        // Open the file in append mode
       for (String sh1: newSh1){
           BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

           // Append the data
           writer.write(sh1);
           writer.newLine(); // Add a new line after the data

           // Close the writer
           writer.close();
       }
    } catch (IOException e) {
        System.err.println("Error: Unable to append data to the file.");
        e.printStackTrace();
       }
    }

    public static boolean doesStringExistInFile( String targetString) {

        String filePath = pathOfDbTextFile;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(targetString)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to read the file.");
            e.printStackTrace();
        }
        return false;
    }

    public static String fileParentDirectory(File file){
        if (file.exists()){
            String parent = file.getParent();
            return parent;
        }
        else
            return null;
    }

    public static void testToZipFile(){
        String textFilePath = "./.AGit/TestSample.txt"; // Replace with the desired file path
        String textContent = "This is a sample text content that will be written to the file.";

        try (PrintWriter writer = new PrintWriter(textFilePath)) {
            writer.println(textContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Text file created: " + textFilePath);
        File textFile = new File(textFilePath);
        if (createAZipFileInCurrentPath(textFile)){
            System.out.println("created zip file!!!!!!!!");
        }
    }

    public static boolean createAZipFileInCurrentPath(File file){
        Sha256 sha = Sha256.getInstance();
         String sourceFilePath = file.getPath();
        String zipFilePath = sourceFilePath+".zip";

        File fileToZip = new File(sourceFilePath);

        try (FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);
             FileInputStream fileInputStream = new FileInputStream(fileToZip)) {

            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean zipFileCreatorInTargetPath(File file) {
        Sha256 sha = Sha256.getInstance();
        String sourceFilePath = file.getPath();
        String targetDirectory = path;
        File fileToZip = new File(sourceFilePath);

        // Create the target directory if it doesn't exist
        File targetDir = new File(targetDirectory);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        String zipFilePath = targetDirectory + File.separator + sha.getHash(fileToZip.getPath()) + ".zip";//fileToZip.getName()

        try (FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);
             FileInputStream fileInputStream = new FileInputStream(fileToZip)) {

            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void createNewTreeFile(FolderFormat entityFormat,String sh1NameOfTree){//the sh1 is the name of the file

        String myContent =  entityFormat.getNameOFFile()+","+entityFormat.getSh1()+","
                +entityFormat.getNameOfEntity()+","+entityFormat.getNameOfCreator()+","+entityFormat.getCreationTime();

        if (!fileAppendLine(path + sh1NameOfTree,myContent,entityFormat.getSh1(),sh1NameOfTree)){

            try (FileWriter writer = new FileWriter(path + sh1NameOfTree)) {//check //todo change it to the path that get from the user
                writer.write(myContent);
            } catch (IOException e) {
                System.out.println(" ");
            }
        }
    }

    public static void writeToFileBuyName(String nameOfParent,FolderFormat entityFormat){
        String currentPath =path  + nameOfParent;

        String myContent = entityFormat.getNameOFFile()+","+entityFormat.getSh1()+","
                +entityFormat.getNameOfEntity()+","+entityFormat.getNameOfCreator()+","+entityFormat.getCreationTime();;

                if (!(nameOfParent.equals(entityFormat.getSh1()))){
                    if (!fileAppendLine(currentPath,myContent,entityFormat.getSh1(),nameOfParent)){
                        createNewTreeFile(entityFormat,nameOfParent);
                    }
                }
    }

    public static boolean fileAppendLine(String path, String lineToAppend,String sh1,String nameOfFile){
        File file = new File(path);
        if (file.exists() && !(doesLineExistInFile(path ,lineToAppend)) && nameOfFile != sh1){
            try {
                //open the file to appending
                BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
                writer.newLine();
                writer.write(lineToAppend);
                writer.close();
                return true;//the file exist and we add a new line
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return false;
        }
    }

    public static boolean doesLineExistInFile(String filePath, String lineToFind) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(lineToFind)) {
                    return true; // Line exists in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Line does not exist in the file or an error occurred
    }

    public static String getSh1HeadFile(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {// Remove the coma
                int commaIndex = line.indexOf(',');

                if (commaIndex != -1) {
                    return line.substring(0, commaIndex);
                } else {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        // Return null if there's an issue reading the file
        return null;
    }

    public static String getDataBetweenCommas(String filePath) {
        StringBuilder data = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split the line by commas
                String[] parts = line.split(",");
                return parts[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.isEmpty())
            return null;
        return data.toString();
    }

    public static void replaceContentBetweenCommas(String filePath, String newContent){
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    parts[1] = newContent; // Replace content between the commas with new content
                    line = String.join(",", parts);
                }
                line += ","; // Add a comma at the end of each line
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileContent.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractStringOfLastCommitBetweenCommas(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) {
                // Assuming the format is "The name ,0f78bb...," (string, comma, string)
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    return parts[1].trim();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Return an empty string if the file format doesn't match expectations
        return "";
    }

    public static String readFileContent(String filePath, String fileName) {
        Path path = Paths.get(filePath, fileName);

        try {
            // Read all lines from the file into a list of strings
            List<String> lines = Files.readAllLines(path);

            // Concatenate the lines into a single string
            StringBuilder content = new StringBuilder();
            for (String line : lines) {
                content.append(line).append(System.lineSeparator());
            }

            return content.toString();
        } catch (IOException e) {
            // Handle the exception (e.g., log it or throw a custom exception)
            e.printStackTrace();
            return null;
        }
    }

    public static String extractLastCommitFromCommitFile(String filePath){
        String contentHeadFile = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Last SHA-1 Content Head File")) {
                    // Extract the content after '=' and trim spaces
                    contentHeadFile = line.split("=")[2].trim();
                    contentHeadFile = contentHeadFile.split(",")[0].trim();
                    break; // Stop searching once found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return contentHeadFile;
    }

    public static void createNewFolderThatRepresentRepositoryForNewUser(String sh1NameFile){

        String folderPath = pathOfDB + File.separator + sh1NameFile;

        File folder = new File(folderPath);

        if (!folder.exists()) {
            boolean folderCreated = folder.mkdir();

            if (folderCreated) {
                createTextFile(folder, "myRepositoriesPaths");
            } else {
                System.err.println("Failed to create folder at: " + folder.getAbsolutePath());
            }
        } else {
            System.out.println("Folder already exists at: " + folder.getAbsolutePath());
        }
    }

    private static void createTextFile(File folder, String fileName) {
        try {
            File file = new File(folder, fileName + ".txt");
            FileWriter writer = new FileWriter(file);

            // Close the FileWriter
            writer.close();

        } catch (IOException e) {
            System.err.println("Failed to create text file. Error: " + e.getMessage());
        }
    }

    public static List<String> getListOfRepoNameBySh1(String sh1){
        List<String> lines = null;
        try {
             lines = readLinesFromFile(pathOfDB, sh1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines ;
    }

    public static List<String> readLinesFromFile(String directoryPath, String fileName) throws IOException {
        Path filePath = Path.of(directoryPath, fileName);
        return Files.readAllLines(filePath);
    }

    public static String getSh1OfLastCommit(String folderPath){

        String content = getContentOfHeadFile(folderPath);
        String sh1Commit = extractContentBetweenCommas(content);
        return sh1Commit;
    }

    private static String getContentOfHeadFile(String folderPath) {

        try {
            Path gitFolderPath = Paths.get(folderPath, ".AGit");

            // Check if the .AGit folder exists
            if (Files.exists(gitFolderPath) && Files.isDirectory(gitFolderPath)) {
                Path headFilePath = gitFolderPath.resolve("Head");

                // Check if the Head file exists
                if (Files.exists(headFilePath) && Files.isRegularFile(headFilePath)) {
                    // Read the content of the Head file
                    return new String(Files.readAllBytes(headFilePath), StandardCharsets.UTF_8);
                } else {
                    return "Head file not found in the .AGit folder.";
                }
            } else {
                return ".AGit folder not found.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while reading the Head file.";
        }
    }

    private static String extractContentBetweenCommas(String input) {
        if (input != null && !input.isEmpty()) {
            int firstCommaIndex = input.indexOf(',');
            int secondCommaIndex = input.indexOf(',', firstCommaIndex + 1);

            if (firstCommaIndex != -1 && secondCommaIndex != -1) {
                return input.substring(firstCommaIndex + 1, secondCommaIndex).trim();
            }
        }

        return "Invalid input format or content not found between commas.";
    }

    public static void createDbFile(String filePath) {
        try {
            // Convert the file path string to a Path object
            Path path = Paths.get(filePath);

            // Create the file if it doesn't exist
            Files.createFile(path);

        } catch (IOException e) {
            System.err.println("Error creating the text file: " + e.getMessage());
        }

    }

    public static String readContentFromZip(String zipFilePath, String fileName,String name) {
        zipFilePath = zipFilePath+fileName + ".zip";
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry entry = zipFile.getEntry(name);

            if (entry != null) {
                return new String(zipFile.getInputStream(entry).readAllBytes());
            } else {
                throw new FileNotFoundException("File not found in the zip: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading content from the zip file: " + e.getMessage(), e);
        }
    }

}