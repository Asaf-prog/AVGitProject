package com.maven.test.avgitproject.dto;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String sh1;
    private String password;

    public UserDTO(String firstName, String lastName, String userName, String email, String sh1, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.sh1 = sh1;
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSh1() {
        return sh1;
    }

    public void setSh1(String sh1) {
        this.sh1 = sh1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", sh1='" + sh1 + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
