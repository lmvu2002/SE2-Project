package com.project.se2project.controller;

import com.project.se2project.model.User;
import com.project.se2project.repository.AdminRepository;
import com.project.se2project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

//    public boolean checkAdmin(String username, String password) {
//        if (adminRepository.findByUsernameAndPassword(username, password) != Admin) {
//            return true;
//        }
//        return false;
//    }

    @GetMapping(value="/admin")
    public String getAllUser(Model model) {
        List<User> listUsers = userRepository.findAll();
        for(User user: listUsers) {
            System.out.println(user);
        }
        return "demo";
    }

    public boolean setInitialBalance(User user, long amount) {
        if (user.isNew() == true) {
            user.add(amount);  //add new balance
            return true;
        }
        return false;
    }
}
