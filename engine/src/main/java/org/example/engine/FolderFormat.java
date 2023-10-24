package org.example.engine;

public class FolderFormat {
    private EntityType nameOfEntity;//can be folder or file(type)
    private String Sh1;
    private String nameOfCreator;
    private String creationTime;

    public FolderFormat(EntityType nameOfEntity, String sh1, String nameOfCreator, String creationTime) {
        this.nameOfEntity = nameOfEntity;
        Sh1 = sh1;
        this.nameOfCreator = nameOfCreator;
        this.creationTime = creationTime;
    }

    public EntityType getNameOfEntity() {
        return nameOfEntity;
    }

    public void setNameOfEntity(EntityType nameOfEntity) {
        this.nameOfEntity = nameOfEntity;
    }

    public String getSh1() {
        return Sh1;
    }

    public void setSh1(String sh1) {
        Sh1 = sh1;
    }

    public String getNameOfCreator() {
        return nameOfCreator;
    }

    public void setNameOfCreator(String nameOfCreator) {
        this.nameOfCreator = nameOfCreator;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
