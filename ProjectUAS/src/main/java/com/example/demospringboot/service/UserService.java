package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.User;
import com.example.demospringboot.repository.UserRepository;
import java.util.Optional;

@Service
public class UserService implements BaseService<User, Integer>{
    @Autowired
    private UserRepository userRepository;

     // Implementasi metode dari BaseService
     @Override
     public List<User> findAll() {
         return userRepository.findAll();
     }
 
     @Override
     public Optional<User> findById(Integer id) {
         return userRepository.findById(id);
     }
 
     @Override
     public User save(User user) {
         return userRepository.save(user);
     }
 
     @Override
     public void deleteById(Integer id) {
            userRepository.deleteById(id);
     }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public User addUser(User user) {
        String pass = user.getPassword();
        String hashedpass = user.getEncryptedPass(pass);
        user.setPassword(hashedpass);
        return userRepository.save(user);
    }
    public User updateUser(int id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUserGrade(int user_id, String grade) {
        userRepository.updateUserGrade(user_id, grade);
    }
    // public User getUserByUsername(String username) {
    //     return userRepository.findByUsername(username);
    // }
}

