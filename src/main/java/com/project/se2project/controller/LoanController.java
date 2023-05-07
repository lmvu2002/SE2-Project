package com.project.se2project.controller;

import com.project.se2project.domain.Loan.*;
import com.project.se2project.model.Loan;
import com.project.se2project.model.User;
import com.project.se2project.repository.LoanRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.service.LoanService;
import com.project.se2project.service.UserService;
import com.project.se2project.service.AdminService;
import com.project.se2project.utils.JwtUtil;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = {"https://summererabanking.com", "http://localhost:8081"}, allowCredentials = "true")
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
    public ResponseEntity<?> makeLoan(@Valid @RequestBody LoanRequest loanRequest, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        JwtUtil jwtUtil = new JwtUtil();

        long userId = jwtUtil.getUserIdFromJWT(jwt);
        User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();

        long inMoney = loanRequest.getInMoney();
        long rate = loanRequest.getRate();
        int duration = loanRequest.getDuration();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        // check if user have loan
        if (loanRepository.findByUser(user).isEmpty()) {
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
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(e.getMessage()));
            }
        }else return ResponseEntity.status(HttpStatus.OK).body(new LoanResponse("Already have a loan"));
    }

    @GetMapping(value = "/loan/check_date")
    public ResponseEntity<?> checkDate(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        try {
            JwtUtil jwtUtil = new JwtUtil();
            long userId = jwtUtil.getUserIdFromJWT(jwt);
            User user = userRepository.findByUsername("0" + String.valueOf(userId)).get();
            List<Loan> loanList = loanRepository.findByUser(user);
            if (loanList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new CheckLoanDateResponse("No loan"));
            }else {
                Loan loan = loanList.get(0);
                String next = loan.getNextPaymentDate();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime nextDate = LocalDate.parse(next, df).atStartOfDay();
                if (now.isAfter(nextDate) || now.isEqual(nextDate)){
                    long pay = loan.getNextPayment();
                    return ResponseEntity.status(HttpStatus.OK).body(new CheckLoanDateResponse("can pay", true, pay));
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(new CheckLoanDateResponse("can't pay", false, 0));
                }
            }
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

    @GetMapping(value = "/loan/{id}")
    public ResponseEntity<?> getLoanById(@PathVariable("id") long id, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) throws NotFoundException {
        try {
            Loan loan = loanService.find(id);
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
