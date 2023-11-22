package org.example.engine;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class sha256 {
    private static sha256 instance = null;
    private sha256() {}
    private static final int[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };
    public static sha256 getInstance() {
        if (instance == null)
            instance = new sha256();
        return instance;
    }

    public static String getHash(String filePath) {
        File fileOrFolder = new File(filePath);
        if (fileOrFolder.exists()) {
            if (fileOrFolder.isDirectory()) {
                // If it's a directory, recursively calculate hash for each file
                return calculateHashForFolder(fileOrFolder);
            } else {
                // If it's a file, calculate hash for the file
                return calculateHashForFile(fileOrFolder);
            }
        } else {
            // If the path doesn't exist, return a default hash based on the name
            return getHashForName(fileOrFolder.getName());
        }
    }
    public static String getHash(File fileOrFolder) {
        if (fileOrFolder.exists()) {
            if (fileOrFolder.isDirectory()) {
                // If it's a directory, recursively calculate hash for each file
                return calculateHashForFolder(fileOrFolder);
            } else {
                // If it's a file, calculate hash for the file
                return calculateHashForFile(fileOrFolder);
            }
        } else {
            // If the path doesn't exist, return a default hash based on the name
            return getHashForName(fileOrFolder.getName());
        }
    }

    private static String getHashForName(String name) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(name.getBytes(StandardCharsets.UTF_8));
            return convertBytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not supported", e);
        }
    }

    private static String calculateHashForFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fileInputStream.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            byte[] bytes = digest.digest();
            return convertBytesToHex(bytes);
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String calculateHashForFolder(File folder) {
        StringBuilder hashBuilder = new StringBuilder();

        for (File file : folder.listFiles()) {
            // Recursively calculate hash for each file in the folder
            hashBuilder.append(getHash(file));
        }

        return hashBuilder.toString();
    }

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder hexBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            hexBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return hexBuilder.toString();
    }
    //  New function that handle the Sh1
    public static String getSh1ForRoot(String path){
        return listFilesAndCalculateSHA1(path);
    }
    private static String listFilesAndCalculateSHA1(String folderPath){
        List<GitFile> fileList = listFilesInFolder(folderPath);
        StringBuilder concatenatedHashes = new StringBuilder();

        for (GitFile filePath : fileList) {
            String sha1Hash = null;
            if (filePath.isBlob()){
                sha1Hash = getHash(filePath.getFile());
            }
            if (!filePath.isBlob()){
                 sha1Hash = getHash(filePath.getPath());
            }
            concatenatedHashes.append(sha1Hash);
        }

        String SH1ForRoot = getHash(concatenatedHashes.toString());
        return SH1ForRoot;
    }

    private static List<GitFile> listFilesInFolder(String folderPath) {
        List<GitFile> fileList = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            listFilesRecursively(folder, "", fileList);
        }

        return fileList;
    }

    private static void listFilesRecursively(File folder, String currentPath, List<GitFile> fileList) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(new gitBlob(currentPath + file.getName(),file));
                } else if (file.isDirectory()) {
                    listFilesRecursively(file, currentPath + file.getName() + "/", fileList);
                    // Include folder names in the concatenation
                    fileList.add(new gitTree(currentPath + file.getName()));
                }
            }
        }
    }

}
