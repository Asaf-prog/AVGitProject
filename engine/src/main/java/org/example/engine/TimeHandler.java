package org.example.engine;

public class TimeHandler {
    private static TimeHandler instance = null;
    private final java.text.SimpleDateFormat formatter;
    private TimeHandler() {
        formatter = new java.text.SimpleDateFormat("dd.MM.yyyy-HH:mm:ss:SSS");
    }
    public static TimeHandler getInstance() {
        if (instance == null)
            instance = new TimeHandler();
        return instance;
    }
    public String getTime() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();
        // Format the current time using the SimpleDateFormat
        return formatter.format(currentTimeMillis);
    }
}
