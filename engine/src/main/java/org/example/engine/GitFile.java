package org.example.engine;

import java.io.File;
import java.util.ArrayList;

public interface GitFile {
    String getShaOne();
    void setShaOne(String sh1);
    String getDate();
    void setDate(String data);
    String getAuthor();
    void setAuthor(String author);
    boolean isBlob();
    String getPath();
    File getFile();
    void addNewFile(GitFile file);
    ArrayList<GitFile>  getFiles();
}
