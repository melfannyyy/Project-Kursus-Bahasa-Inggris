package com.example.demospringboot.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
public class Admin extends User {
    //access level bermanfaat untuk pengembangan aplikasi lebih lanjut yang membutuhkan beberapa level admin
    @Column(name = "access_level", nullable = true)
    private Integer accessLevel;

    // Getters dan Setters
    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }
}
