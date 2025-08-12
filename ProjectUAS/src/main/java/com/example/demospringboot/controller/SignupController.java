package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demospringboot.service.UserService;
import com.example.demospringboot.entity.Member;
import org.springframework.ui.Model;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;


    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("userInfo", new Member());
        return "signup"; 
    }

    @PostMapping(value="/signup")
    public String saveUser(Model model, Member userInfo)
    {   
        userService.addUser(userInfo);

        // // Melakukan autentikasi pengguna setelah signup
        // UsernamePasswordAuthenticationToken token = 
        //     new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
        
        // // Otentikasi menggunakan AuthenticationManager
        //  authenticationManager.authenticate(token);

        // // Menyimpan informasi pengguna yang sudah terautentikasi di SecurityContext
        // SecurityContextHolder.getContext().setAuthentication(token);

        //vArahkan ke halaman home setelah login otomatis
        
        return "user/user-success";
    }
}
