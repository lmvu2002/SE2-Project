package com.project.se2project.controller;

import com.project.se2project.domain.Transaction.*;
import com.project.se2project.model.Loan;
import com.project.se2project.model.Saving;
import com.project.se2project.service.LoanService;
import com.project.se2project.service.SavingService;
import com.project.se2project.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.project.se2project.model.Transaction;
import com.project.se2project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
@RequestMapping(value = "/transaction/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    LoanService loanService;

    @Autowired
    SavingService savingService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/")
    public ResponseEntity<CreateTransactionResponse> createTransaction(@CookieValue(name = "jwt", defaultValue = "dark") String jwt, @Valid @RequestBody CreateTransactionRequest createTransactionRequest) {
        try {

            Long toUserId = transactionService.getIdByUsername(createTransactionRequest.getToUserUsername());
            Long fromUserId = transactionService.getIdByUsername(createTransactionRequest.getFromUserUsername());
            if (toUserId == null || fromUserId == null) {
                throw new Exception("User not found");
            }
            Transaction transaction = transactionService.createTransaction(fromUserId, toUserId, createTransactionRequest.getAmount(),createTransactionRequest.getTransactionTime(), jwt);

            CreateTransactionResponse createTransactionResponse = new CreateTransactionResponse("Create transaction successfully");
            return ResponseEntity.status(HttpStatus.OK).body(createTransactionResponse);
        } catch (Exception e) {
            CreateTransactionResponse createTransactionResponse = new CreateTransactionResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(createTransactionResponse);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<GetTransactionResponse> getTransaction(@CookieValue(name = "jwt", defaultValue = "dark") String jwt, @PathVariable int id) {
        try {
            Transaction transaction = transactionService.getTransactionById(id, jwt);
            GetTransactionResponse getTransactionResponse = new GetTransactionResponse(transaction);
            return ResponseEntity.status(HttpStatus.OK).body(new GetTransactionResponse(transaction));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetTransactionResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<GetAllTransactionResponse> getAllTransactions(@CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            List<Transaction> allTransaction = transactionService.getAllTransactions(jwt);
            List<GetTransactionResponse> getTransactionResponseList = new ArrayList<>();
            allTransaction.forEach(transaction -> {
                getTransactionResponseList.add(new GetTransactionResponse(transaction));
            });
            return ResponseEntity.status(HttpStatus.OK).body(new GetAllTransactionResponse(getTransactionResponseList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetAllTransactionResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/log/{index}")
    public ResponseEntity<GetTransactionLogResponse> getTransactionLogByIndex(@CookieValue(name = "jwt", defaultValue = "dark") String jwt, @PathVariable int index) {
        try {
            Long userId = transactionService.getUserIdByUsername(jwtUtil.getUsernameFromJWT(jwt));
            System.out.println(userId);
            Loan loan = loanService.findLoanByUserId(userId);
            System.out.println("Loan:"+loan);
            List<Saving> savingList = savingService.findSavingByUserId(userId);
            System.out.println(savingList);
            List<Transaction> transferList = transactionService.getAllTransactionsByUserId(userId);
            System.out.println(transferList);
            List<GetTransactionLogResponse> getTransactionLogResponseList = new ArrayList<>();
            transferList.forEach(transaction -> {
                getTransactionLogResponseList.add(new GetTransactionLogResponse(transaction));
            });
            if (savingList != null || savingList.size() != 0) {
                savingList.forEach(saving -> {
                    getTransactionLogResponseList.add(new GetTransactionLogResponse(saving));
                });
            }
            if (loan != null) {
                getTransactionLogResponseList.add(new GetTransactionLogResponse(loan));
            }

            //sort the list by getDate()
            getTransactionLogResponseList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            return ResponseEntity.status(HttpStatus.OK).body(getTransactionLogResponseList.get(index));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetTransactionLogResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/log/all")
    public ResponseEntity<GetAllTransactionLogResponse> getAllTransactionLog( @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            Long userId = transactionService.getUserIdByUsername(jwtUtil.getUsernameFromJWT(jwt));
            Loan loan = loanService.findLoanByUserId(userId);
            List<Saving> savingList = savingService.findSavingByUserId(userId);
            List<Transaction> transferList = transactionService.getAllTransactionsByUserId(userId);
            List<GetTransactionLogResponse> getTransactionLogResponseList = new ArrayList<>();
            transferList.forEach(transaction -> {
                getTransactionLogResponseList.add(new GetTransactionLogResponse(transaction));
            });
            if (savingList != null || savingList.size() != 0) {
                savingList.forEach(saving -> {
                    getTransactionLogResponseList.add(new GetTransactionLogResponse(saving));
                });
            }
            if (loan != null) {
                getTransactionLogResponseList.add(new GetTransactionLogResponse(loan));
            }
            //sort the list by getDate()
            getTransactionLogResponseList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            System.out.println(getTransactionLogResponseList.size());
            return ResponseEntity.status(HttpStatus.OK).body(new GetAllTransactionLogResponse(getTransactionLogResponseList));
        } catch (Exception e) {
            System.out.println("ERROR BITCH");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetAllTransactionLogResponse(e.getMessage()));
        }
    }

    @GetMapping(value="/log/user/{userId}")
    public ResponseEntity<GetAllTransactionLogResponse> getAllTransactionLogByUserId(@PathVariable Long userId) {
        try {

            Loan loan = loanService.findLoanByUserId(userId);
            List<Saving> savingList = savingService.findSavingByUserId(userId);
            List<Transaction> transferList = transactionService.getAllTransactionsByUserId(userId);
            List<GetTransactionLogResponse> getTransactionLogResponseList = new ArrayList<>();
            transferList.forEach(transaction -> {
                getTransactionLogResponseList.add(new GetTransactionLogResponse(transaction));
            });
            if (savingList != null && savingList.size() != 0) {
                savingList.forEach(saving -> {
                    getTransactionLogResponseList.add(new GetTransactionLogResponse(saving));
                });
            }
            if (loan != null) {
                getTransactionLogResponseList.add(new GetTransactionLogResponse(loan));
            }
            //sort the list by getDate()
            getTransactionLogResponseList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
            System.out.println(getTransactionLogResponseList.size());
            return ResponseEntity.status(HttpStatus.OK).body(new GetAllTransactionLogResponse(getTransactionLogResponseList));
        } catch (Exception e) {
            System.out.println("ERROR BITCH");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetAllTransactionLogResponse(e.getMessage()));
        }
    }



    @GetMapping(value = "/{page}")
    public ResponseEntity<GetAllTransactionResponse> getAllTransactionPerPage(@PathVariable int page, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {
            List<Transaction> transactionList = transactionService.getAllTransactionsPerPage(page);
            if (transactionList == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetAllTransactionResponse("Out of range"));
            }
            List<GetTransactionResponse> getTransactionResponseList = new ArrayList<>();
            transactionList.forEach(transaction -> {
                getTransactionResponseList.add(new GetTransactionResponse(transaction));
            });
            return ResponseEntity.status(HttpStatus.OK).body(new GetAllTransactionResponse(getTransactionResponseList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetAllTransactionResponse(e.getMessage()));
        }
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity<GetAllTransactionResponse> getAllTransactionsByUsername(@PathVariable String username, @CookieValue(name = "jwt", defaultValue = "dark") String jwt) {
        try {

            Long userId = transactionService.getUserIdByUsername(username);
            List<Transaction> transactionList = transactionService.getAllTransactionsByUserId(userId);
            List<GetTransactionResponse> getTransactionResponseList = new ArrayList<>();
            transactionList.forEach(transaction -> {
                getTransactionResponseList.add(new GetTransactionResponse(transaction));
            });
            return ResponseEntity.status(HttpStatus.OK).body(new GetAllTransactionResponse(getTransactionResponseList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new GetAllTransactionResponse(e.getMessage()));
        }
    }
}
