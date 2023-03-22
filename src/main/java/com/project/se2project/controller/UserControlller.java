package com.project.se2project.controller;

import com.project.se2project.model.User;
import com.project.se2project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserControlller {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/")
    public String getAllUser(Model model) {
        List<User> listUsers = userRepository.findAll();
        for(User user: listUsers) {
            System.out.println(user);
        }
        return "demo";
    }

}
