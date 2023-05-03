package com.project.se2project.controller;

import com.project.se2project.domain.Saving.*;
import com.project.se2project.domain.User.GetAllUserResponse;
import com.project.se2project.domain.User.GetUserResponse;
import com.project.se2project.model.Saving;
import com.project.se2project.model.User;
import com.project.se2project.repository.SavingRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.service.AdminService;
import com.project.se2project.service.SavingService;
import com.project.se2project.service.UserService;
import com.project.se2project.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
public class SavingController {

    @Autowired
    private SavingService savingService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    SavingRepository savingRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/admin/savings")
    public ResponseEntity<?> getAllSavings(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            List<Saving> savings = savingService.findAllSaving(jwt);
            List<GetSavingResponse> savingResponses = new ArrayList<>();
            for (Saving saving : savings) {
                savingResponses.add(new GetSavingResponse(saving));
            } return ResponseEntity.status(HttpStatus.OK).body( new GetAllSavingResponse(savingResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllSavingResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/admin/saving_user")
    public ResponseEntity<?> getUserInSaving(@CookieValue(name = "jwt", defaultValue = "dark") String jwt){
        try {
            List<User> users = userService.getAllUser(jwt);
            List<GetUserSavingResponse> res = new ArrayList<>();
            int size = 0;
            for ( User user : users ){
                if(savingService.hasSaving(user)){
                    size = savingService.findSavingByUserId(user.getId()).size();
                    res.add(new GetUserSavingResponse(user, size));
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(new GetAllUserSavingResponse(res));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllSavingResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/admin/saving_user/{id}")
    public ResponseEntity<?> getAUserSaving(@PathVariable("id") long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt){
        try {
            User user = userService.getUserById(id, jwt);
            List<Saving> savings = savingService.findSavingByUserId(id);
            List<GetSavingResponse> savingResponses = new ArrayList<>();
            for (Saving saving : savings) {
                savingResponses.add(new GetSavingResponse(saving));
            } return ResponseEntity.status(HttpStatus.OK).body( new GetAllSavingResponse(savingResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllSavingResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/admin/savings/{id}")
    public ResponseEntity<?> getSavingById(@PathVariable("id") long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt){
        try {
            Saving saving = savingService.findSavingById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new GetSavingResponse(saving));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetSavingResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/make_saving")
    public ResponseEntity<?> makeSaving(@Valid @RequestBody SavingRequest savingRequest, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {

        long userId = jwtUtil.getUserIdFromJWT(jwt);
        User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();

        long money = savingRequest.getMoney();
        String startDate = savingRequest.getStartDate();
        long rate = savingRequest.getRate();

        if(money >= user.getBalance()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetSavingResponse("Not enough money"));
        } else {
            try {
                Saving saving = savingService.makeSaving(
                        user,
                        money,
                        startDate,
                        rate);
                adminService.manageUserBalance(user.getId(), - money);
                return ResponseEntity.status(HttpStatus.OK).body(new GetSavingResponse(saving));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetSavingResponse(e.getMessage()));
            }
        }
    }


    @GetMapping(value = "/user/savings")
    public ResponseEntity<?> getUserSaving(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            JwtUtil jwtUtil = new JwtUtil();
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();
            long id = user.getId();
            List<Saving> savings = savingService.findSavingByUserId(id);
            List<GetSavingResponse> savingResponses = new ArrayList<>();
            for (Saving saving : savings) {
                savingResponses.add(new GetSavingResponse(saving));
            } return ResponseEntity.status(HttpStatus.OK).body( new GetAllSavingResponse(savingResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllSavingResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/user/savings/{id}/withdraw")
    public ResponseEntity<?> withdrawSaving(@PathVariable("id") long id,
                                            @RequestBody Map<String, Object> requestBody,
                                            @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            if (userId != savingService.findSavingById(id).getUser().getId()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetSavingResponse("Not your saving"));
            }
            long money = Long.parseLong(requestBody.get("money").toString());
            Saving saving = savingService.takeMoneyFromSaving(id, money);
            return ResponseEntity.status(HttpStatus.OK).body(new GetSavingResponse(saving));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetSavingResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/admin/savings/{id}/edit")
    public ResponseEntity<?> editSaving(@PathVariable("id") long id,
                                        @RequestBody Map<String, Object> requestBody,
                                        @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            long rate = Long.parseLong(requestBody.get("rate").toString());
            savingService.updateSaving(id, rate, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new SavingResponse("Update saving successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SavingResponse(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/user/savings/{id}/delete")
    public ResponseEntity<?> deleteSaving(@PathVariable("id") long id,
                                          @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            savingService.deleteSaving(id, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new SavingResponse("Delete saving successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SavingResponse(e.getMessage()));
        }
    }
}
