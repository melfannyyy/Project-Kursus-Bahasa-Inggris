package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demospringboot.service.InstructorService;
import com.example.demospringboot.entity.Instructor;
import org.springframework.ui.Model;

@Controller
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    // menampilkan halaman dengan daftar instructor
    @GetMapping("/instructor")
    public String showListInstructor(Model model){
        model.addAttribute("instructorList", instructorService.getAllInstructors()); 
        model.addAttribute("instructorInfo", new Instructor());
        return "instructor/instructor-list.html";
    }

    @GetMapping("/instructor/new")
    public String showForm(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor/instructor-form"; // Nama file template (Thymeleaf)
    }

    // Proses tambah instructor
    @PostMapping("/instructor/save")
    public String saveInstructor(Instructor instructor) {
        instructorService.addInstructor(instructor);
        return "redirect:/instructor";
    }

    // Menampilkan halaman add, edit instructor
    @GetMapping("/instructor/{id}")
    public String editInstructor(Model model, @PathVariable("id") int id) {
        model.addAttribute("instructor", instructorService.getInstructorById(id)); 
        return "instructor/instructor-form"; 
    }

    // Proses edit instructor
    @PostMapping("/instructor/edit")
    public String updateInstructor(Instructor instructor) {
        // perubahan active status tidak mempengaruhi enrollment
        instructorService.updateInstructor(instructor.getId(),instructor);
        return "redirect:/instructor";
    }
}
