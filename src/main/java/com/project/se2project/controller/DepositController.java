package com.project.se2project.controller;


import com.project.se2project.domain.Deposit.*;
import com.project.se2project.model.Deposit;
import com.project.se2project.model.User;
import com.project.se2project.repository.DepositRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.service.AdminService;
import com.project.se2project.service.DepositService;
import com.project.se2project.service.UserService;
import com.project.se2project.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(value = "/admin/deposits")
    public ResponseEntity<?> getAllDeposits(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            List<Deposit> deposits = depositService.findAllDeposit(jwt);
            List<GetDepositResponse> depositResponses = new ArrayList<>();
            for (Deposit deposit : deposits) {
                depositResponses.add(new GetDepositResponse(deposit));
            } return ResponseEntity.status(HttpStatus.OK).body( new GetAllDepositResponse(depositResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllDepositResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/admin/deposit_user")
    public ResponseEntity<?> getUserInDeposit(@CookieValue(name = "jwt", defaultValue = "dark") String jwt){
        try {
            List<User> users = userService.getAllUser(jwt);
            List<GetUserDepositResponse> res = new ArrayList<>();
            int size = 0;
            for ( User user : users) {
                if (depositService.hasDeposit(user)){
                    size = depositService.findDepositByUser(user).size();
                    res.add(new GetUserDepositResponse(user, size));
                }
            } return ResponseEntity.status(HttpStatus.OK).body( new GetAllUserDepositResponse(res));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllUserDepositResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/admin/deposit_user/{id}")
    public ResponseEntity<?> getDepositByUser(@PathVariable("id") long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt){
        try {
            User user = userService.getUserById(id, jwt);
            List<Deposit> deposits = depositService.findDepositByUser(user);
            List<GetDepositResponse> res = new ArrayList<>();
            for (Deposit deposit : deposits) {
                res.add(new GetDepositResponse(deposit));
            } return ResponseEntity.status(HttpStatus.OK).body( new GetAllDepositResponse(res));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllDepositResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/admin/deposit/{id}")
    public ResponseEntity<?> getDepositById(@PathVariable("id") long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt){
        try {
            Deposit deposit = depositService.findDepositById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new GetDepositResponse(deposit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetDepositResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/make_deposit")
    public ResponseEntity<?> makeDeposit(@RequestBody DepositRequest depositRequest, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {

        long userId = jwtUtil.getUserIdFromJWT(jwt);
        User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();

        long money = depositRequest.getMoney();
        String startDate = depositRequest.getStartDate();
        long rate = depositRequest.getRate();
        int duration = depositRequest.getDuration();
        String endDate = depositRequest.getEndDate();
        long totalMoney = depositRequest.getTotalMoney();

        try {
            Deposit deposit = depositService.makeDeposit(
                    user,
                    money,
                    startDate,
                    rate,
                    duration,
                    endDate,
                    totalMoney
            );
            adminService.manageUserBalance(user.getId(), -money);
            return ResponseEntity.status(HttpStatus.OK).body(new GetDepositResponse(deposit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DepositResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/user/deposits")
    public ResponseEntity<?> getAllDepositsByUser(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();
            List<Deposit> deposits = depositService.findDepositByUser(user);
            List<GetDepositResponse> depositResponses = new ArrayList<>();
            for (Deposit deposit : deposits) {
                depositResponses.add(new GetDepositResponse(deposit));
            } return ResponseEntity.status(HttpStatus.OK).body( new GetAllDepositResponse(depositResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetAllDepositResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/user/deposit/{id}")
    public ResponseEntity<?> getDepositByIdUser(@PathVariable("id") long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt){
        try {
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();
            Deposit deposit = depositService.findDepositById(id);
            if (deposit.getUser().getId() == user.getId()) {
                return ResponseEntity.status(HttpStatus.OK).body(new GetDepositResponse(deposit));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetDepositResponse("You are not the owner of this deposit"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetDepositResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/user/deposit/{id}/withdraw")
    public ResponseEntity<?> withdrawDeposit(@PathVariable("id") long id,
                                             @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();
            Deposit deposit = depositService.findDepositById(id);
            if (Objects.equals(deposit.getUser().getId(), user.getId())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate today = LocalDate.now();
                LocalDate endDate = LocalDate.parse(deposit.getEndDate(), formatter);
                if (today.isBefore(endDate)) {
                    long alterMoney = deposit.getMoney() - deposit.getMoney() * 5 / 100;
                    adminService.manageUserBalance(user.getId(), alterMoney);
                    depositService.takeMoney(id);
                    return ResponseEntity.status(HttpStatus.OK).body(new GetDepositResponse("Money taken"));
                }
                adminService.manageUserBalance(user.getId(), deposit.getTotalMoney());
                depositService.takeMoney(id);
                return ResponseEntity.status(HttpStatus.OK).body(new GetDepositResponse("Money taken"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetDepositResponse("You are not the owner of this deposit"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetDepositResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/admin/deposit/{id}/edit")
    public ResponseEntity<?> editDeposit(@PathVariable("id") long id,
                                         @RequestBody Map<String, Object> requestBody,
                                         @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            long rate = Long.parseLong(requestBody.get("rate").toString());
            depositService.updateDeposit(id, rate, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new GetDepositResponse("OK"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetDepositResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/admin/deposit/{id}/delete")
    public ResponseEntity<?> deleteDeposit(@PathVariable("id") long id,
                                           @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            depositService.deleteDeposit(id, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new GetDepositResponse("OK"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetDepositResponse(e.getMessage()));
        }
    }
}
