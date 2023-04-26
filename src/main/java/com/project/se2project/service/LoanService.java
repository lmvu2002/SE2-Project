package com.project.se2project.service;

import com.auth0.jwt.JWT;
import com.project.se2project.domain.User.LoanRequest;
import com.project.se2project.model.Loan;
import com.project.se2project.model.User;
import com.project.se2project.repository.LoanRepository;
import com.project.se2project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    public List<Loan> findAll(){return loanRepository.findAll();};

    public Loan find(long id){ return loanRepository.findById(id).get();};

    public void save(Loan loanAccount) {
        loanRepository.save(loanAccount);
    }

    public void delete(long id){
        loanRepository.deleteById(id);
    };

    public Loan makeLoan(User user, Long inMoney, String startDate, int duration, long rate , String jwt) throws Exception {
        // create a new loan object and set its properties
        Loan loan = new Loan(user ,inMoney, startDate, duration, rate);
        // save the loan to the database
        loanRepository.save(loan);
        return loan;
    }
    public Loan updateLoan (long id, long rate, String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        } else {
            Loan loan = loanRepository.findById(id).get();
            if (loan == null) {
                throw new Exception("Loan not found");
            }
            loan.setRate(rate);
            loan.setTotalMoney(loan.getInMoney(), rate, loan.getDuration());
            loan.setNextPayment(loan.getInMoney(), rate, loan.getDuration());
            loanRepository.save(loan);
            return loan;
        }
    }

    public List<Loan> getLoanByAdmin(String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        } else {
            return loanRepository.findAll();
        }
    }

    public Loan findLoanByUserId(long id) throws Exception {
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new Exception("User not found");
        }
        Loan loan = loanRepository.findByUser(user).get(0);
        if (loan == null) {
            throw new Exception("Loan not found");
        }
        return loan;
    }

    public Loan monthlyPayment(String jwt, long payment) throws Exception {
        String username = JWT.decode(jwt).getSubject();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("User not found");
        }
        Loan loan = loanRepository.findByUser(user).get(0);
        if (loan == null) {
            throw new Exception("Loan not found");
        }
        if (loan.getUser().getId() != user.getId()) {
            throw new Exception("You are not the owner of this loan");
        }
        if (loan.getDuration() == 0) {
            throw new Exception("Loan is already paid");
        }
        String payDay = loan.getNextPaymentDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formattedPayDay = LocalDate.parse(payDay, formatter);
        LocalDate today = LocalDate.now();
        if (today.isBefore(formattedPayDay)) {
            throw new Exception("You can't pay before the due date");
        } else {
            int newDuration = loan.getDuration() - 1;
            long newPaidMoney = loan.getPaidMoney() + payment;
            loan.setDuration(newDuration);
            loan.setPaidMoney(newPaidMoney);
            long remainMoney = loan.getRemainMoney() - newPaidMoney;
            loan.setRemainMoney(remainMoney);
            if (remainMoney == 0) {
                loan.setDuration(0);
                loan.setPaid(true);
            }
            LocalDate nexPayDay = formattedPayDay.plusMonths(1);
            loan.setNextPaymentDate(nexPayDay.format(formatter));
            loan.setNextPayment(remainMoney, loan.getRate(), newDuration);
            loanRepository.save(loan);
            return loan;
        }
    }

    public void deleteLoanByAdmin(long id, String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        } else {
            Loan loan = loanRepository.findById(id).get();
            if (loan == null) {
                throw new Exception("Loan not found");
            }
            long loanMoney = loan.getInMoney();
            User user = loan.getUser();
            user.setBalance(user.getBalance() - loanMoney);
            loanRepository.delete(loan);
        }
    }
}
