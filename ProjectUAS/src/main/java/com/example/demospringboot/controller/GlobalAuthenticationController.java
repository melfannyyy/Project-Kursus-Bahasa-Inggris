package com.example.demospringboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAuthenticationController {

    @ModelAttribute
    public void addAuthentication(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            // global variable, supaya bisa diakses di semua halaman
            // var username buat cek di view (thymeleaf) apakah sudah login belum
            // System.out.println("hello saya ada di global controller");
            model.addAttribute("username", authentication.getName());
        }
    }
}

