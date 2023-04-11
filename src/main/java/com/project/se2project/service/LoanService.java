package com.project.se2project.service;

import com.project.se2project.domain.User.LoanRequest;
import com.project.se2project.model.Loan;
import com.project.se2project.model.User;
import com.project.se2project.repository.LoanRepository;
import com.project.se2project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

//    public void acceptLoanAccount(Loan loanAccount);

    public void makeLoan(LoanRequest loanRequest, String jwt) throws Exception {
        // get the user from the user id in the request
        User user = userService.getUserById(loanRequest.getUserId(), jwt);

        // create a new loan object and set its properties
        Loan loan = new Loan();
//        loan.set(loanRequest.getRepaymentSchedule());

        // save the loan to the database
        loanRepository.save(loan);

        // update the user's balance
        Long newBalance = user.getBalance() + loanRequest.getAmount();
        adminService.manageUserBalance(user.getId(), newBalance);
    }
}
