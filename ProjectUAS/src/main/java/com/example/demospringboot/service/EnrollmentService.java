package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Enrollment;
import com.example.demospringboot.repository.EnrollmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service


public class EnrollmentService implements BaseService<Enrollment, Integer> {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Implementasi metode dari BaseService
    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Optional<Enrollment> findById(Integer id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteById(Integer id) {
        enrollmentRepository.deleteById(id);
    }



    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getAllActiveEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAllActive();

        //urutkan dulu dengan sorted() kemudian ditampung ke collector
        return enrollments.stream()
        .sorted((e1, e2) -> e2.getJadwal().getGrade().compareTo(e1.getJadwal().getGrade())) // Urutkan Descending Pemula, Menengah, Lanjutan (p,m,l)
        .collect(Collectors.toList());
        
        //return enrollmentRepository.findAllActive();
    }

    public List<Enrollment> getFilteredEnrollmentsByInstructor(int instructorId) {
        List<Enrollment> enrollments = enrollmentRepository.findAllActive(); // Ambil semua data Enrollment
    
        // Filter berdasarkan instructor_id dari entitas Jadwal
        return enrollments.stream()
            .filter(enrollment -> enrollment.getJadwal().getInstructor().getId() == instructorId)
            .collect(Collectors.toList());
    }

    public List<Enrollment> getFilteredEnrollmentsByGrade(String grade) {
        List<Enrollment> enrollments = enrollmentRepository.findAllActive(); // Ambil semua data Enrollment
    
        // Filter berdasarkan instructor_id dari entitas Jadwal
        return enrollments.stream()
            .filter(enrollment -> enrollment.getJadwal().getGrade().equals(grade))
            .collect(Collectors.toList());
    }

    // public Enrollment getEnrollmentById(Long id) {
    //     return enrollmentRepository.findById(id).orElse(null);
    // }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment findByActiveUser(int user_id){
        return enrollmentRepository.findByUserId(user_id);
    }

    public void updateInactiveByUser(int user_id){
        enrollmentRepository.updateInactiveByUser(user_id);
    }

    public void updateInactiveByJadwal(int jadwal_id){
        enrollmentRepository.updateInactiveByJadwal(jadwal_id);
    }

    public int countEnrollmentByUser(int id) {
        return enrollmentRepository.countByUser(id);
    }

    public Enrollment getLastEnrollmentByUser(int id)
    {
        return enrollmentRepository.findLastByUser(id);
    }

}

