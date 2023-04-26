package com.project.se2project.controller;

import com.project.se2project.domain.User.*;
import com.project.se2project.model.Loan;
import com.project.se2project.model.User;
import com.project.se2project.repository.LoanRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.service.LoanService;
import com.project.se2project.service.UserService;
import com.project.se2project.service.AdminService;
import com.project.se2project.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.project.se2project.constant.SecurityConstant.COOKIE_EXPIRIED;
import static java.lang.Long.parseLong;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
@RequestMapping(value = "/user/")
public class LoanController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoanService loanService;

    @Autowired
    UserService userService;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/loan")
    public ResponseEntity<?> makeLoan(@Valid @RequestBody LoanRequest loanRequest,@CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        JwtUtil jwtUtil = new JwtUtil();

        String newJwt = jwt.replace("Bearer ", "");

//        Optional<User> optionalUser  = userRepository.findById(jwtUtil.getUserIdFromJWT(jwt));
        long userId = jwtUtil.getUserIdFromJWT(jwt);
//        System.out.println("userId: " + userId);
        User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();


        long inMoney = loanRequest.getInMoney();
        long rate = loanRequest.getRate();
        int duration = loanRequest.getDuration();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        try {
            Loan loan = loanService.makeLoan(
                    user,
                    inMoney,
                    formattedDateTime,
                    duration,
                    rate,
                    jwt
            );
            adminService.manageUserBalance(user.getId(), inMoney);
            return ResponseEntity.status(HttpStatus.OK).body(new LoanResponse(loan));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/loan/delete/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable("id") long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        try {
            Loan loan = loanRepository.findById(id).orElseThrow(() -> new NotFoundException("Loan not found"));
            User user = loan.getUser();
            long loanMoney = loan.getInMoney();
            adminService.manageUserBalance(user.getId(), -loanMoney);
            loanService.deleteLoanByAdmin(id, jwt);
            return ResponseEntity.status(HttpStatus.OK).body(new LoanResponse("Delete loan successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @GetMapping(value = "/all_loan")
    public ResponseEntity<?> getAllLoan(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        try {
            List<Loan> loans = loanService.getLoanByAdmin(jwt);
            List<GetLoanResponse> loanResponses = new ArrayList<>();
            for (Loan loan : loans) {
                loanResponses.add(new GetLoanResponse(loan));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new GetAllLoanResponse(loanResponses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @PostMapping(value = "/loan/edit/{id}")
    public ResponseEntity<?> editLoan(@PathVariable("id") long id,
                                      @RequestBody Map<String, Object> requestBody,
                                      @CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        try {
            long rate = Long.parseLong(requestBody.get("rate").toString());
            Loan loan = loanRepository.findById(id).orElseThrow(() -> new NotFoundException("Loan not found"));
            User user = loan.getUser();
            loanService.updateLoan(id, rate, jwt);
            loanRepository.save(loan);
            return ResponseEntity.status(HttpStatus.OK).body(new LoanResponse("Edit loan successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @GetMapping(value = "/loan/my_loan")
    public ResponseEntity<?> getLoanByUserAndId(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        try {
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            User user = userRepository.findByUsername("0" + userId).get();
            long id = user.getId();
            Loan loan = loanService.findLoanByUserId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new GetLoanResponse(loan));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }

    @PostMapping(value = "/loan/pay")
    public ResponseEntity<?> payLoan(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        try {
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            User user = userRepository.findByUsername("0" + userId).get();
            long id = user.getId();
            Loan loan = loanService.findLoanByUserId(id);
            long thisMonthLoan = loan.getNextPayment();
            long userBalance = user.getBalance();
            System.out.println("userBalance: " + userBalance);
            if (userBalance >= thisMonthLoan) {
                loanService.monthlyPayment(jwt, thisMonthLoan);
                adminService.manageUserBalance(id, -thisMonthLoan);
                return ResponseEntity.status(HttpStatus.OK).body(new LoanResponse("Pay loan successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Not enough money"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
        }
    }
}
