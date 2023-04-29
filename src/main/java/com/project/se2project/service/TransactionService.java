package com.project.se2project.service;

import com.project.se2project.custom_exception.AuthException;
import com.project.se2project.custom_exception.NotEnoughMoneyException;
import com.project.se2project.model.Admin;
import com.project.se2project.model.Transaction;
import com.project.se2project.model.User;
import com.project.se2project.repository.AdminRepository;
import com.project.se2project.repository.TransactionRepository;
import com.project.se2project.repository.UserRepository;
import com.project.se2project.utils.JwtUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JwtUtil jwtUtil;

    private UserService userService = new UserService();

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    //create transaction
    public Transaction createTransaction(long fromUserId, long toUserId, int amount, String transactionTime, String jwt) throws NotFoundException, NotEnoughMoneyException {
            User fromUser = getUserById(fromUserId);
            User toUser = getUserById(toUserId);
            if (fromUser.getBalance() < amount) {
                throw new NotEnoughMoneyException("Not enough money in your account");
            }
            fromUser.setBalance(fromUser.getBalance() - amount);
            toUser.setBalance(toUser.getBalance() + amount);
            transactionTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            Transaction transaction = new Transaction(fromUser.getId(), toUser.getId(), amount, transactionTime);
        System.out.println("transaction:"+transaction);
            transactionRepository.save(transaction);
        System.out.println("Save successfully");
            return transaction;

    }

    public Transaction getTransactionById(long id, String jwt) throws NotFoundException {
            return transactionRepository.findById(id).get();
    }


    public List<Transaction> getAllTransactions(String jwt) {
        return transactionRepository.findAll();

    }

    //Trả về 8 transaction theo page, page đánh số từ 1
    public List<Transaction> getAllTransactionsPerPage(int pageNumber) {

            List<Transaction> transactionPage = new ArrayList<>();
            List<Transaction> transactions = transactionRepository.findAll();

            int startIndex = (pageNumber - 1)*4;
            int endIndex = Math.max(startIndex + 4, transactions.size());

            for (int i = startIndex; i < endIndex; i++) {
                transactionPage.add(transactionRepository.findById(transactions.get(i).getTransactionId()).get());
            }
            return transactionPage;


    }

    public List<Transaction> getAllTransactionsByUserId(long id, String jwt) throws NotFoundException {
            List<Transaction> fromUserTransactions = transactionRepository.findAllByFromUserId(id);
            List<Transaction> toUserTransactions = transactionRepository.findAllByToUserId(id);
            //merge and sort 2 lists together by transactionTime
            List<Transaction> allTransactions = new ArrayList<>();
            allTransactions.addAll(fromUserTransactions);
            allTransactions.addAll(toUserTransactions);
            allTransactions.sort((t1, t2) -> t2.getTransactionTime().compareTo(t1.getTransactionTime()));
            return allTransactions;
    }

    //Find all transactions by transaction amount
    public List<Transaction> getAllTransactionsByAmount(int amount, String jwt) {

            return transactionRepository.findAllByAmount(amount);

    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
    }

    public Long getUserIdByUsername(String username) {
        System.out.println("User:"+userRepository.findByUsername(username));
        return userRepository.findByUsername(username).getId();
    }


//    private void isAdminJwt(String jwt) {
//        if (!jwtUtil.validateToken(jwt)) {
//            throw new AuthException("Invalid token");
//        }
//
//        Admin admin = adminRepository.findByName(jwtUtil.getUsernameFromJWT(jwt));
//
//        if (admin == null) {
//            throw new AuthException("Invalid Admin");
//        }
//    }

//    public boolean isAdminJwtCheck(String jwt) {
//        if (!jwtUtil.validateToken(jwt)) {
//            throw new AuthException("Invalid token");
//        }
//        Admin admin = adminRepository.findByName(jwtUtil.getUsernameFromJWT(jwt));
//
//        return admin != null;
//    }

//    private void isUserJwt(String jwt) {
//        if (!jwtUtil.validateToken(jwt)) {
//            throw new AuthException("Invalid token");
//        }
//        System.out.println(jwtUtil.getUsernameFromJWT(jwt));
//        User user = userService.loadUserByUsername(jwtUtil.getUsernameFromJWT(jwt));
//
//        if (user == null) {
//            throw new AuthException("Invalid User");
//        }
//    }

//    private boolean isUserJwtCheck(String jwt) {
//        if (!jwtUtil.validateToken(jwt)) {
//            throw new AuthException("Invalid token");
//        }
//        System.out.println("Check user with jwt:"+jwtUtil.getUsernameFromJWT(jwt));
//        User user = userRepository.findByUsername(jwtUtil.getUsernameFromJWT(jwt));
//
//        return user != null;
//    }
}
