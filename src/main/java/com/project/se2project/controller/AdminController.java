package com.project.se2project.controller;

import com.project.se2project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    AdminRepository adminRepository;


}
