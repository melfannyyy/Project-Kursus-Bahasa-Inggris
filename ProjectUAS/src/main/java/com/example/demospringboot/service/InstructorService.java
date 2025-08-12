package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Instructor;
import com.example.demospringboot.repository.InstructorRepository;
import java.util.List;
import java.util.Optional;


@Service
public class InstructorService implements BaseService<Instructor, Integer> {
    @Autowired
    private InstructorRepository instructorRepository;

     // Implementasi metode dari BaseService
     @Override
     public List<Instructor> findAll() {
         return instructorRepository.findAll();
     }
 
     @Override
     public Optional<Instructor> findById(Integer id) {
         return instructorRepository.findById(id);
     }
 
     @Override
     public Instructor save(Instructor instructor) {
         return instructorRepository.save(instructor);
     }
 
     @Override
     public void deleteById(Integer id) {
         instructorRepository.deleteById(id);
     }


    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(int id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public Instructor addInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }
   
    public void deleteInstructor(int id) {
        instructorRepository.deleteById(id);
    }

    

    public Instructor updateInstructor(int id, Instructor instructor) {
        instructor.setId(id);
        return instructorRepository.save(instructor);
    }

    public List<Instructor> getAllActiveInstructors() {
        return instructorRepository.findAllActive();
    }

}
