package com.project.se2project.service;

import com.project.se2project.custom_exception.AlreadyException;
import com.project.se2project.custom_exception.AuthException;
import com.project.se2project.domain.User.UpdateUserRequest;
import com.project.se2project.model.Admin;
import com.project.se2project.model.User;
import com.project.se2project.repository.AdminRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.utils.JwtUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public User signUp(String username, String name, String password, String dob) {
        User user = userRepository.findByUsername(username);
        Admin admin = adminRepository.findByName(username);

        if (admin != null) {
            throw new AlreadyException("This name was taken by another Admin");
        }

        if (user != null) {
            throw new AlreadyException("This username was taken by another User");
        }

        if (password.length() < 5 || password.length() > 15) {
            throw new AuthException("Password must be between 5 and 15 characters long");
        }

        String startingDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        User newUser = new User(username, name, passwordEncoder.encode(password), dob, true, "Wait", 0L, startingDate);
        userRepository.save(newUser);

        return newUser;
    }

    public String signIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        Admin admin = adminRepository.findByName(username);

        if (user == null) {
            if (admin == null) {
                throw new AuthException("Wrong password or username");
            }

            if (!passwordEncoder.matches(password, admin.getPassword())) {
                throw new AuthException("Wrong password or username");
            }

            return jwtUtil.generateToken(admin);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException("Wrong password or username");
        }

        return jwtUtil.generateToken(user);

    }

    public boolean isAdmin(String username) {
        Admin admin = adminRepository.findByName(username);
        return admin != null;
    }

    public void deleteUser(long userId, String jwt) throws NotFoundException {
        isAdminJwt(jwt);

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        userRepository.delete(user.get());
    }

    public List<User> getAllUser(String jwt) {
        isAdminJwt(jwt);

        return userRepository.findAll();
    }


    public User getUserById(long id, String jwt) throws NotFoundException {
        if (!jwtUtil.validateToken(jwt)) {
            throw new AuthException("Invalid token");
        }

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        return user.get();
    }

    public User updateUser(long id, UpdateUserRequest updateUserRequest, String jwt) throws NotFoundException {
        isAdminJwt(jwt);

        Optional<User> currentUser = userRepository.findById(id);

        if (currentUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        currentUser.get().setDob(updateUserRequest.getDob());
        currentUser.get().setBalance(updateUserRequest.getBalance());
        currentUser.get().setNew(updateUserRequest.isNew());
        currentUser.get().setUsername(updateUserRequest.getPhone());
        currentUser.get().setName(updateUserRequest.getName());

        userRepository.save(currentUser.get());

        return currentUser.get();
    }

    private void isAdminJwt(String jwt) {
        if (!jwtUtil.validateToken(jwt)) {
            throw new AuthException("Invalid token");
        }

        Admin admin = adminRepository.findByName(jwtUtil.getUsernameFromJWT(jwt));

        if (admin == null) {
            throw new AuthException("Invalid Admin");
        }
    }

    public boolean isAdminJwtCheck(String jwt) {
        if (!jwtUtil.validateToken(jwt)) {
            throw new AuthException("Invalid token");
        }
        Admin admin = adminRepository.findByName(jwtUtil.getUsernameFromJWT(jwt));

        return admin != null;
    }
}
