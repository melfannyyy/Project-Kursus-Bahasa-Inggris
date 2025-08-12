package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
// Interface

public interface InstructorRepository
extends JpaRepository<Instructor, Integer> {
    @Query(value = "SELECT * FROM instructor WHERE active_status = 1", nativeQuery = true)
    List<Instructor> findAllActive();
}
