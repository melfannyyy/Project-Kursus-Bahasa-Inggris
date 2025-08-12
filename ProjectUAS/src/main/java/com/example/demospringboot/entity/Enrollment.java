package com.example.demospringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @ManyToOne // Anotasi untuk membuat foreign key
    @JoinColumn(name = "user_id", nullable = false) // Foreign key ke tabel Member
    private User user;

    @ManyToOne // Anotasi untuk membuat foreign key
    @JoinColumn(name = "jadwal_id", nullable = false) // Foreign key ke tabel Member
    private Jadwal jadwal;

    @Column(name = "active_status")
    private boolean active_status;


    //SETTER GETTER
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getTanggal() {
        return tanggal;
    }
    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Jadwal getJadwal() {
        return jadwal;
    }
    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }

    public boolean isActive_status() {
        return active_status;
    }
    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }

}
