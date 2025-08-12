package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demospringboot.service.JadwalService;
import com.example.demospringboot.service.EnrollmentService;
import com.example.demospringboot.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demospringboot.entity.Jadwal;
import org.springframework.ui.Model;

@Controller

public class JadwalController {
    @Autowired
    private JadwalService jadwalService;    
    
    @Autowired
    private InstructorService instructorService;

    @Autowired
    private EnrollmentService enrollmentService;    

    @GetMapping("/jadwal")
    public String showListJadwal(Model model){
        model.addAttribute("jadwalList", jadwalService.getAllActiveJad());

        return "jadwal/jadwal-list.html";
    }

    @GetMapping("/jadwal/new")
    public String showForm(Model model) {
        model.addAttribute("jadwal", new Jadwal());
        model.addAttribute("instructors", instructorService.getAllActiveInstructors());
        String[] arrDay = new String[0];
        model.addAttribute("arrDay",arrDay);
        return "jadwal/jadwal-form"; 
    }

    // Proses tambah jadwal
    @PostMapping("/jadwal/save")
    public String saveJadwal(Jadwal jadwal) {
        jadwal.setActive_status(true);
        jadwalService.addJad(jadwal);
        return "redirect:/jadwal";
    }

     // Menampilkan halaman 
     @GetMapping("/jadwal/{id}")
     public String editJadwal(Model model, @PathVariable("id") int id) {
         Jadwal jadwal = jadwalService.getJadById(id);
         //System.out.println("jadwal form:"+jadwal.getGrade());
         model.addAttribute("instructors", instructorService.getAllActiveInstructors());
         model.addAttribute("jadwal", jadwal);
         // Convert `hari` string into a list to pre-select checkboxes in view
         String[] arrDay = jadwal.getDay() != null ? jadwal.getDay().split(",") : new String[0];
         model.addAttribute("arrDay", arrDay); 
         return "jadwal/jadwal-form"; // Gunakan template yang sama dengan form tambah
     }

       // Proses edit instructor
    @PostMapping("/jadwal/edit")
    public String updateJadwal(Jadwal jadwal) {
        //System.out.println("Grade edit:"+jadwal.getGrade());
        jadwalService.updateJad(jadwal.getId(),jadwal);
        return "redirect:/jadwal";
    }

    @PostMapping("/jadwal/delete/{id}")
    public String deleteJadwal(@PathVariable("id") int id) {
        //System.out.println("Grade edit:"+jadwal.getGrade());
        //jadwalService.updateJad(jadwal.getId(),jadwal);
        //soft delete
        jadwalService.updateInactiveById(id);
        enrollmentService.updateInactiveByJadwal(id);
        System.out.println(id);
        return "redirect:/jadwal";
    }
}
