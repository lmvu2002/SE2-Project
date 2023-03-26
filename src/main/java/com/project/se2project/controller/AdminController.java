package com.project.se2project.controller;

import com.project.se2project.domain.Admin.AdminSignInRequest;
import com.project.se2project.domain.Admin.AdminSignInResponse;
import com.project.se2project.domain.Admin.AdminSignUpRequest;
import com.project.se2project.domain.Admin.AdminSignUpResponse;
import com.project.se2project.model.Admin;
import com.project.se2project.service.AdminService;
import com.project.se2project.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.se2project.constant.SecurityConstant.COOKIE_EXPIRIED;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/signup")
    public ResponseEntity<AdminSignUpResponse> signup(@Valid @RequestBody AdminSignUpRequest adminSignUpRequest, HttpServletResponse response) {
        try {
            Admin admin = adminService.signUp(
                    adminSignUpRequest.getAdminName(),
                    adminSignUpRequest.getPassword(),
                    adminSignUpRequest.getSecretKey(),
                    adminSignUpRequest.getDob());

            final String jwt = jwtUtil.generateToken(admin);
            AdminSignUpResponse signupResponse = new AdminSignUpResponse(jwt, "Signup thành công");

            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setPath("/");
            cookie.setMaxAge(COOKIE_EXPIRIED);

            response.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body(signupResponse);
        } catch (Exception e) {
            AdminSignUpResponse signupResponse = new AdminSignUpResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(signupResponse);
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<AdminSignInResponse> signin(@Valid @RequestBody AdminSignInRequest adminSignInRequest, HttpServletResponse response) {
        try {
            String jwt = adminService.signIn(adminSignInRequest.getAdminName(), adminSignInRequest.getPassword());
            AdminSignInResponse adminSignInResponse = new AdminSignInResponse(jwt, "Đăng nhập thành công");

            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setPath("/");
            cookie.setMaxAge(COOKIE_EXPIRIED);

            response.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body(adminSignInResponse);

        } catch (Exception e) {
            AdminSignInResponse adminSignInResponse = new AdminSignInResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(adminSignInResponse);
        }
    }
}
