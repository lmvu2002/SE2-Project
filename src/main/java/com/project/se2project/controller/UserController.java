package com.project.se2project.controller;

import com.project.se2project.domain.User.*;
import com.project.se2project.model.User;
import com.project.se2project.service.UserService;
import com.project.se2project.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.project.se2project.constant.SecurityConstant.COOKIE_EXPIRIED;

@RestController
@RequestMapping(value = "/user/")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignUpRequest userSignUpRequest, HttpServletResponse response) {
        try {
            User user = userService.signUp(
                    userSignUpRequest.getUsername(),
                    userSignUpRequest.getPassword(),
                    userSignUpRequest.getPhone(),
                    userSignUpRequest.getDob()
            );

            final String jwt = jwtUtil.generateToken(user);
            UserSignUpResponse userSignUpResponse = new UserSignUpResponse(jwt, "Signup successful");

            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setPath("/");
            cookie.setMaxAge(COOKIE_EXPIRIED);

            response.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body(userSignUpResponse);
        } catch (Exception e) {
            UserSignUpResponse userSignUpResponse = new UserSignUpResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userSignUpResponse);
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<UserSignInResponse> signin(@Valid @RequestBody UserSignInRequest userSignInRequest, HttpServletResponse response) {
        try {
            String jwt = userService.signIn(userSignInRequest.getUsername(), userSignInRequest.getPassword());
            UserSignInResponse userSignInResponse = new UserSignInResponse(jwt, "Signin successful");

            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setPath("/");
            cookie.setMaxAge(COOKIE_EXPIRIED);

            response.addCookie(cookie);

            return ResponseEntity.status(HttpStatus.OK).body(userSignInResponse);

        } catch (Exception e) {
            UserSignInResponse userSignInResponse = new UserSignInResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(userSignInResponse);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            userService.deleteUser(id, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new DeleteUserResponse("Delete successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DeleteUserResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<GetAllUserResponse> getAllUser(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            List<User> allUser = userService.getAllUser(jwt);
            List<GetUserResponse> getUserResponseList = new ArrayList<>();
            allUser.forEach((user) -> {
                GetUserResponse getUserResponse = new GetUserResponse(user);
                getUserResponseList.add(getUserResponse);
            });

            return ResponseEntity.status(HttpStatus.OK).body(new GetAllUserResponse(getUserResponseList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GetAllUserResponse(e.getMessage()));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable long id,
            @Valid @RequestBody UpdateUserRequest updateUserRequest,
            @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            User user = userService.updateUser(id, updateUserRequest, jwt);

            return ResponseEntity.status(HttpStatus.OK).body(new UpdateUserResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UpdateUserResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            User user = userService.getUserById(id, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new GetUserResponse(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }
}
