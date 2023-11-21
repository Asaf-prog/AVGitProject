package com.maven.test.avgitproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "email")
    private String email;

    @Column(name = "sh1")
    private String sh1;

    @Column(name = "password")
    private String password;

    public User(){}

    public User(String firstName, String lastName, String email, String sh1, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sh1 = sh1;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", sh1='" + sh1 + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
