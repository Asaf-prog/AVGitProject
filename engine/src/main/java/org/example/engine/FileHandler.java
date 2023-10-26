package org.example.engine;

import java.io.*;


import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileHandler {
    private static FileHandler instance = null;
    private static String path = "./.AGit/.Object/";
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
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void setPath(String p){
        path = p+ "/.AGit/.Object/";
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
                   // if (file.isFile()) { // Check if it's a file (not a directory)
                        filesInDirectory.add(file);
                   // }
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

        if (folder.exists() && folder.isDirectory()) {
            listFilesRecursively(folder, fileList);
        }

        return fileList;
    }

    private static void listFilesRecursively(File folder, List<File> fileList) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    // If it's a directory, recursively list its files
                    listFilesRecursively(file, fileList);
                }
            }
        }
    }



    public static void writeToDBFile(ArrayList<String> newSh1){//write new sh1 to db file
       try{
           String filePath = "C:\\Users\\asafr\\OneDrive\\מסמכים\\GitHub\\AVGitProject\\engine\\src\\main\\java\\org\\example\\engine\\test.txt";
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
        if (createAZipFileInCurrentPath(textFile)){
            System.out.println("created zip file!!!!!!!!");
        }
    }
    public static boolean createAZipFileInCurrentPath(File file){
        sha256 sha = sha256.getInstance();
       // String sourceFilePath = path + sha.getHash(file.getName());
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
        sha256 sha = sha256.getInstance();
        String sourceFilePath = file.getPath();
        String targetDirectory = path;
        File fileToZip = new File(sourceFilePath);

        // Create the target directory if it doesn't exist
        File targetDir = new File(targetDirectory);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        String zipFilePath = targetDirectory + File.separator + sha.getHash(fileToZip.getName()) + ".zip";

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

        try (FileWriter writer = new FileWriter(path + sh1NameOfTree)) {//check //todo change it to the path that get from the user
            writer.write(myContent);
        } catch (IOException e) {
            System.err.println("Failed to open the file for writing.");
        }
    }
    public static void writeToFileBuyName(String nameOfParent,FolderFormat entityFormat){
        String currentPath =path  + nameOfParent;

        String myContent = entityFormat.getNameOFFile()+","+entityFormat.getSh1()+","
                +entityFormat.getNameOfEntity()+","+entityFormat.getNameOfCreator()+","+entityFormat.getCreationTime();;

        if (!fileAppendLine(currentPath,myContent)){
            createNewTreeFile(entityFormat,nameOfParent);
        }
    }
    public static boolean fileAppendLine(String path, String lineToAppend){
        File file = new File(path);
        if (file.exists() && !(doesLineExistInFile(path ,lineToAppend))){
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
            // Handle any IO exceptions, such as file not found
            e.printStackTrace();
        }
        return false; // Line does not exist in the file or an error occurred
    }
}