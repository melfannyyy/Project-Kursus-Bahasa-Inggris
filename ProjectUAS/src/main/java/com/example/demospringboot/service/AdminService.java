package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Admin;
import com.example.demospringboot.repository.AdminRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements BaseService<Admin, Integer>{
    @Autowired
    private AdminRepository adminRepository;

    // Implementasi metode dari BaseService
    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> findById(Integer id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }


    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    public Admin getAdminById(int id) {
        return adminRepository.findById(id).orElse(null);
    }
    public Admin addAdmin(Admin admin) {
        String pass = admin.getPassword();
        String hashedpass = admin.getEncryptedPass(pass);
        admin.setPassword(hashedpass);
        return adminRepository.save(admin);
    }
    public Admin updateAdmin(int id, Admin admin) {
        admin.setId(id);
        return adminRepository.save(admin);
    }
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }
    
    // public Admin getAdminByAdminname(String adminname) {
    //     return adminRepository.findByAdminname(adminname);
    // }

    // public void updateAdminGrade(int admin_id, String grade) {
    //     adminRepository.updateAdminGrade(admin_id, grade);
    // }
}