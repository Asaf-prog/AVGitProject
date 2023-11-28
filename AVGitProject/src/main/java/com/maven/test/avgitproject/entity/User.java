package com.maven.test.avgitproject.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "user_name")
    private String userName;


    @Column(name = "email")
    private String email;

    @Column(name = "sh1")
    private String sh1;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_detail_id")
    private UserDetail userDetail;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",
    cascade = {CascadeType.ALL})
    private List<Sh1Detail> sh1Details;

    public User(){}

    public User(String firstName, String lastName, String userName, String email, String sh1, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Sh1Detail> getSh1Details() {
        return sh1Details;
    }

    public void setSh1Details(List<Sh1Detail> sh1Details) {
        this.sh1Details = sh1Details;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", sh1='" + sh1 + '\'' +
                ", password='" + password + '\'' +
                ", userDetail=" + userDetail +
                ", sh1Details=" + sh1Details +
                '}';
    }
    public void add(Sh1Detail tempSh1Detail) {
        if (this.sh1Details == null){
            this.sh1Details = new ArrayList<>();
        }
        this.sh1Details.add(tempSh1Detail);
        tempSh1Detail.setUser(this);
    }
}
