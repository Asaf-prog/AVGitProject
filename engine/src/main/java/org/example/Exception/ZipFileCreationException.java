package org.example.Exception;

public class ZipFileCreationException extends Exception {
    public ZipFileCreationException() {
        super("Failed to create the zip file.");
    }

    public ZipFileCreationException(String message) {
        super(message);
    }
}
