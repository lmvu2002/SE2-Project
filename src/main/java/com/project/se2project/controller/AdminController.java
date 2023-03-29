package com.project.se2project.controller;

import com.project.se2project.domain.Admin.*;
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

    @PostMapping(value = "/signin")
    public ResponseEntity<AdminSignInResponse> signin(@Valid @RequestBody AdminSignInRequest adminSignInRequest, HttpServletResponse response) {
        try {
            String jwt = adminService.signIn(adminSignInRequest.getName(), adminSignInRequest.getPassword());
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

    @PostMapping(value = "/{id}/setBalance")
    public ResponseEntity<AdminSetBalanceResponse> setBalance(@PathVariable(value = "id") Long id, @RequestBody AdminSetBalanceRequest AdminSetBalanceRequest) {
        try {
            adminService.setUserBalance(id, AdminSetBalanceRequest.getBalance());
            AdminSetBalanceResponse adminSetBalanceResponse = new AdminSetBalanceResponse("Set balance successfully");
            return ResponseEntity.status(HttpStatus.OK).body(adminSetBalanceResponse);
        } catch (Exception e) {
            AdminSetBalanceResponse adminSetBalanceResponse = new AdminSetBalanceResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(adminSetBalanceResponse);
        }
    }

    @PostMapping(value = "/{id}/manageBalance")
    public ResponseEntity<AdminManageBalanceResponse> manageBalance(@PathVariable(value = "id") Long id, @RequestBody AdminManageBalanceRequest AdminManageBalanceRequest) {
        try {
            adminService.manageUserBalance(id, AdminManageBalanceRequest.getAmount());
            AdminManageBalanceResponse adminManageBalanceResponse = new AdminManageBalanceResponse("Manage balance successfully");
            return ResponseEntity.status(HttpStatus.OK).body(adminManageBalanceResponse);
        } catch (Exception e) {
            AdminManageBalanceResponse adminManageBalanceResponse = new AdminManageBalanceResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(adminManageBalanceResponse);
        }
    }
}
