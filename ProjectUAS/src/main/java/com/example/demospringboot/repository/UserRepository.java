package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demospringboot.entity.User;

@Repository
// Interface

public interface UserRepository 
extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value="UPDATE user SET grade = :grade WHERE id = :user_id", nativeQuery = true)
    int updateUserGrade(int user_id, String grade);
}
