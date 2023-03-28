package com.project.se2project.service;

import com.project.se2project.custom_exception.AlreadyException;
import com.project.se2project.custom_exception.AuthException;
import com.project.se2project.model.Admin;
import com.project.se2project.model.User;
import com.project.se2project.repository.AdminRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.utils.JwtUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.project.se2project.constant.SecurityConstant.*;
import static com.project.se2project.utils.JwtUtil.SECRET_KEY_JWT;

@Service
public class AdminService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    public Admin loadAdminByName(String name) throws UsernameNotFoundException {
        return adminRepository.findByName(name);
    }

    public Admin getAdminById(Long adminId) throws NotFoundException {
        try {
            Optional<Admin> admin = adminRepository.findById(adminId);
            if (admin.isEmpty()) {
                throw new NotFoundException("Admin with id " + admin + " not found");
            }
            return admin.get();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            throw e;
        }
    }

    public Admin signUp(String name, String password, String secretKey, String dob) throws NotFoundException {
        if (!secretKey.equals(SECRET_KEY_JWT)) {
            throw new AuthException("Secret Key sai");
        }

        Admin admin = adminRepository.findByName(name);
        User user = userRepository.findByUsername(name);

        if (admin != null) {
            throw new AlreadyException("This name was taken by another Admin");
        }

        if (user != null) {
            throw new AlreadyException("This username was taken by another User");
        }

        if (password.length() < 5 || password.length() > 15) {
            throw new AuthException("Password must be between 5 and 15 characters long");
        }

        Admin newAdmin = new Admin(name, passwordEncoder.encode(password), dob);

        adminRepository.save(newAdmin);
        return newAdmin;
    }

    public String signIn(String name, String password) {
        Admin mainAdmin = adminRepository.findByName(ADMIN_NAME);

        if (mainAdmin == null) {
            Admin newMainAdmin = new Admin(ADMIN_NAME, passwordEncoder.encode(ADMIN_PASSWORD), ADMIN_DOB);
            adminRepository.save(newMainAdmin);
        }

        Admin admin = adminRepository.findByName(name);

        if (admin == null) {
            throw new AuthException("Invalid Password or username");
        }

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new AuthException("Invalid Password or username");
        }

        return jwtUtil.generateToken(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
