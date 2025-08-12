package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
// import org.springframework.data.jpa.repository.Query;
// import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
}
