package com.project.se2project.service;

import com.project.se2project.custom_exception.AlreadyException;
import com.project.se2project.custom_exception.AuthException;
import com.project.se2project.model.Admin;
import com.project.se2project.model.User;
import com.project.se2project.repository.AdminRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User signUp(String username, String password, String phone, String dob) {
        User user = userRepository.findByUsername(username);
        Admin admin = adminRepository.findByAdminName(username);

        if (admin != null) {
            throw new AlreadyException("This name was taken by another Admin");
        }

        if (user != null) {
            throw new AlreadyException("This name was taken by another User");
        }

        if (password.length() < 5 || password.length() > 15) {
            throw new AuthException("Password must be between 5 and 15 characters long");
        }

        User newUser = new User(username, passwordEncoder.encode(password), phone, dob, true, "Wait", 0L);
        userRepository.save(newUser);

        return newUser;
    }

    public String signIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AuthException("Wrong password or username");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException("Wrong password or username");
        }


        return jwtUtil.generateToken(user);
    }

    public void deleteUser(long userId, String jwt) {
        if (!jwtUtil.validateToken(jwt)) {
            throw new AuthException("Invalid token");
        }

        Admin admin = adminRepository.findByAdminName(jwtUtil.getUsernameFromJWT(jwt));

        if (admin == null) {
            throw new AuthException("Invalid Admin");
        }

        User user = userRepository.findById(userId);
        userRepository.delete(user);
    }
}
