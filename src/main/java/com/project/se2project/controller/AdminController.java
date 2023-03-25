package com.project.se2project.controller;

import com.project.se2project.model.User;
import com.project.se2project.repository.AdminRepository;
import com.project.se2project.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Controller
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    UserRepository userRepository;

    public boolean setPassword(Long id, String oldPassword, String newPassword) {
        if (Objects.equals(oldPassword, newPassword)) {
            User user = userRepository.findById(id).get();
            user.setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping(value="/add")
    public User addUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping(value="delete/{id}")
    public boolean deleteUser(@PathVariable(value="id") Long id) {
        if (userRepository.existsById(id)) {
            User deletingUser = userRepository.getById(id);
            userRepository.delete(deletingUser);
            return true;
        } else {
            return false;
        }

    }

    @PostMapping(value="/update/{id}")
    public void updateUser(@Valid @PathVariable(value="id")Long id,@Valid @RequestBody User newUser) {
        if (userRepository.existsById(id)) {
            newUser.setId(id);
            userRepository.save(newUser);
        }
    }


}
