package com.example.demospringboot.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
// import java.time.LocalDateTime;

// tambahan
// import java.time.format.DateTimeFormatter;  

@Entity
@Table(name = "jadwal")
public class Jadwal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "grade")
    private String grade;

    @Column(name="day")
    private String day;

    @Column(name="time")
    private String time;

    @Column(name = "active_status")
    private boolean active_status;

    @ManyToOne // Anotasi untuk membuat foreign key
    @JoinColumn(name = "instructor_id", nullable = false) // Foreign key ke tabel Member
    private Instructor instructor;

    public Jadwal() {}
    public Jadwal(String grade, Instructor instructor) {
        this.grade = grade;
        // this.tanggal_waktu = tanggal_waktu;
        // this.user = user;
        this.instructor = instructor;
    }

    // SETTER & GETTER
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public boolean isActive_status() {
        return active_status;
    }
    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
