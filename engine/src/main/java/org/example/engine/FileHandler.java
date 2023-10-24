package org.example.engine;

import java.io.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileHandler {
    private static FileHandler instance = null;
    private FileHandler() { }
    public static FileHandler getInstance() {
        if (instance == null)
            instance = new FileHandler();
        return instance;
    }
    // Method to write content to a file
    public static void writeToFile(String filePath, String... contentArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : contentArray) {
                writer.write(line);
                writer.write(","); // Add ',' between data members
            }
            System.out.println("File write successful.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
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
                    if (file.isFile()) { // Check if it's a file (not a directory)
                        filesInDirectory.add(file);
                    }
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } else {
            System.out.println("The specified directory does not exist or is not a directory.");
        }

        return filesInDirectory;

    }
    public static void writeToDBFile(ArrayList<String> newSh1){//write new sh1 to db file
       try{String filePath = "C:\\Users\\asafr\\OneDrive\\מסמכים\\GitHub\\AVGitProject\\engine\\src\\main\\java\\org\\example\\engine\\test.txt";
        // Open the file in append mode
       for (String sh1: newSh1){
           BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

           // Append the data
           writer.write(sh1);
           writer.newLine(); // Add a new line after the data

           // Close the writer
           writer.close();
          // System.out.println("Data appended to file successfully.");
       }
    } catch (IOException e) {
        System.err.println("Error: Unable to append data to the file.");
        e.printStackTrace();
       }
    }
    public static boolean doesStringExistInFile( String targetString) {
        String filePath="C:\\Users\\asafr\\OneDrive\\מסמכים\\GitHub\\AVGitProject\\engine\\src\\main\\java\\org\\example\\engine\\test.txt";
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
        if (createAZipFile(textFile)){
            System.out.println("created zip file!!!!!!!!");
        }
    }
    public static boolean createAZipFile(File file){
        String sourceFilePath = file.getPath();
        String zipFilePath = sourceFilePath+".zip";

        File fileToZip = new File(sourceFilePath);

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(fileToZip)) {

            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static void createNewTreeFile(String nameOfParent, String sh1){//the sh1 is the name of the file

    }
    public static void writeToFileBuyName(String nameOfParent,FolderFormat folderFormat){

    }
}