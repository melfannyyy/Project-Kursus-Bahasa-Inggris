package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
// import com.example.demo.security.SecurityUtil; // Import kelas SecurityUtil

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demospringboot.service.EnrollmentService;
// import com.example.demo.service.JadwalService;
import com.example.demospringboot.service.UserService;
import com.example.demospringboot.entity.Enrollment;
import com.example.demospringboot.entity.User;


@Controller
public class SiteController {
    @Autowired
    private UserService userService;  
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/home"; // Halaman yang dapat diakses tanpa login
    }

    @GetMapping("/home")
    public String home2(Model model) {
        
        return "home";
    }

    @GetMapping("/assessment")
    public String showAssessment()
    {
        return "assessment";
    }

    @GetMapping("/about")
    public String showAbout(Model model) {
        
        return "about"; // Halaman admin
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
         // Mendapatkan informasi pengguna yang terautentikasi
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
         // Menambahkan role ke model
         model.addAttribute("roles", authentication.getAuthorities().toString());
         model.addAttribute("username", authentication.getName());
        
       
        User user = userService.getUserByUsername(authentication.getName());
        Enrollment enrollment = enrollmentService.findByActiveUser(user.getId());
       
        Enrollment lastenrollment = enrollmentService.getLastEnrollmentByUser(user.getId());
        if(lastenrollment == null)
        {
            // belum pernah ikut, dan tidak punya grade
            System.out.println("belum pernah ikut, dan tidak punya grade");
        }
        else
        {
            System.out.println("sudah pernah ikut, dan punya grade");
            model.addAttribute("lastenrollment",lastenrollment);
        }
        model.addAttribute("enrollment",enrollment);
        model.addAttribute("user",user);
        
         return "dashboard"; // Halaman dashboard
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Halaman login
    }

    @GetMapping("/course")
    public String showMateriKursus() {
        return "course"; // Halaman dummy course
    }

}
