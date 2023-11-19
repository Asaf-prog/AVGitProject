package org.example.engine;

import java.io.File;

public interface gitFile {
    String getShaOne();
    String getDate();
    String getAuthor();
    boolean isBlob();
    String getPath();
    File getFile();
}
