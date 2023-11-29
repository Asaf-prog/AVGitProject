package com.maven.test.avgitproject.dto;

public class Sha1DetailDTO {
    private String sh1;
    private String name;
    private String path;

    public Sha1DetailDTO(){}

    public Sha1DetailDTO(String sh1, String name, String path) {
        this.sh1 = sh1;
        this.name = name;
        this.path = path;
    }

    public String getSh1() {
        return sh1;
    }

    public void setSh1(String sh1) {
        this.sh1 = sh1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Sha1DetailDTO{" +
                "sh1='" + sh1 + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
