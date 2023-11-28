package com.maven.test.avgitproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sh1repositories")
public class Sh1Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sh1")
    private String sh1;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Sh1Detail(){}

    public Sh1Detail(String sh1, String name, String path) {
        this.sh1 = sh1;
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Sh1Detail{" +
                "id=" + id +
                ", sh1='" + sh1 + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
