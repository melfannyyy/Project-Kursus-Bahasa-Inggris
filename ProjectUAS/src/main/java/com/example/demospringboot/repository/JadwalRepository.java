package com.example.demospringboot.repository;
import com.example.demospringboot.entity.Jadwal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, Integer> {
    //tambahan, supaya hanya memilih jadwal yang aktif
    @Query(value = "SELECT * FROM jadwal WHERE active_status = 1 AND grade= :grade", nativeQuery = true)
    public List<Jadwal> findBygrade(String grade);

    @Query(value = "SELECT * FROM jadwal WHERE active_status = 1", nativeQuery = true)
    List<Jadwal> findAllActive();

    @Modifying
    @Transactional
    @Query(value="UPDATE jadwal SET active_status = 0 WHERE id = :jadwal_id", nativeQuery = true)
    int updateInactiveById(int jadwal_id);
    
   
}
