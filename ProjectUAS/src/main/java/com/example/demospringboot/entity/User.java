package com.example.demospringboot.entity;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user")

public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
   
    @Column(name = "email")
    private String email;

    // @Column(name = "firstname")
    // private String firstname;

    // @Column(name = "lastname")
    // private String lastname;

    // @Column(name="birthdate")
    // private LocalDate birthdate;   

    // @Column(name="grade")
    // private String grade;

    public User() {}

    // auth
    public User(String username, String password, String role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // public User(String firstname, String lastname,String email, LocalDate birthdate, String password, String role) {
    //     this.firstname = firstname;
    //     this.lastname = lastname;
    //     this.password = password;
    //     this.email = email;
    //     this.birthdate = birthdate;
    //     this.role = role;
    // }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // public void setFirstname(String firstname) {
    //     this.firstname = firstname;
    // }

    // public String getFirstname() {
    //     return firstname;
    // }

    // public String getLastname() {
    //     return lastname;
    // }

    // public void setLastname(String lastname) {
    //     this.lastname = lastname;
    // }

    // public LocalDate getBirthdate() {
    //     return birthdate;
    // }

    // public void setBirthdate(LocalDate birthdate) {
    //     this.birthdate = birthdate;
    // }

    // public String getFullName() {
    //     return firstname + " " + lastname;
    // }

    // public String getFormattedBirthdate()
    // {
    //     if (birthdate == null) {
    //         return "";
    //     }
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    //     String formattedDateTime = birthdate.format(formatter);
    //     return formattedDateTime;
    // }

    // public String getGrade() {
    //     return grade;
    // }

    // public void setGrade(String grade) {
    //     this.grade = grade;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public String getEncryptedPass(String pass)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pass);
        return hashedPassword;
    }

    public void setEncryptedPass()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(this.password);
        setPassword(hashedPassword);
    }

   

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role)); // Misalkan role adalah string
    }
}
