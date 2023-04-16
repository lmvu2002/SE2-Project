package com.project.se2project.service;

import com.project.se2project.custom_exception.AuthException;
import com.project.se2project.model.Admin;
import com.project.se2project.model.Transaction;
import com.project.se2project.model.User;
import com.project.se2project.repository.AdminRepository;
import com.project.se2project.repository.TransactionRepository;
import com.project.se2project.utils.JwtUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JwtUtil jwtUtil;

    private UserService userService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions(String jwt) {
        isAdminJwt(jwt);

        return transactionRepository.findAll();
    }

    //Trả về 8 transaction theo page, page đánh số từ 1
    public List<Transaction> getAllTransactionsPerPage(String jwt, int pageNumber) {
        isAdminJwt(jwt);

        List<Transaction> transactionPage = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findAll();

        int startIndex = (pageNumber - 1)*8;
        int endIndex = Math.max(startIndex + 8, transactions.size());

        for (int i = startIndex; i < endIndex; i++) {
            transactionPage.add(transactionRepository.findById(transactions.get(i).getTransactionId()).get());
        }
        return transactionPage;
    }

    public List<Transaction> getAllTransactionsByUserId(long id, String jwt) throws NotFoundException {
        isAdminJwt(jwt);
        return transactionRepository.findAllByFromUserId(id);
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
}
