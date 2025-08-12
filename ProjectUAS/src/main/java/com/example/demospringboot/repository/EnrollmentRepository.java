package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Enrollment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    // custome native query tidak berdasarkan JPA

    @Query(value = "SELECT * FROM enrollment WHERE user_id = :user_id AND active_status = 1", nativeQuery = true)
    Enrollment findByUserId(int user_id);

    @Query(value = "SELECT * FROM enrollment WHERE active_status = 1", nativeQuery = true)
    List<Enrollment> findAllActive();

    // update dengan tiga anotasi

    @Modifying
    @Transactional
    @Query(value="UPDATE enrollment SET active_status = 0 WHERE user_id = :user_id", nativeQuery = true)
    int updateInactiveByUser(int user_id);

    @Modifying
    @Transactional
    @Query(value="UPDATE enrollment SET active_status = 0 WHERE jadwal_id = :jadwal_id", nativeQuery = true)
    int updateInactiveByJadwal(int jadwal_id);

    @Query(value = "SELECT COUNT(*) FROM enrollmment WHERE user_id = :user_id", nativeQuery = true)
    Integer countByUser(int user_id);

    @Query(value="SELECT * FROM enrollment WHERE user_id = :user_id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Enrollment findLastByUser(int user_id);

}