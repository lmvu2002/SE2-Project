package com.project.se2project.service;

import com.project.se2project.model.Deposit;
import com.project.se2project.model.User;
import com.project.se2project.repository.DepositRepository;
import com.project.se2project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    public void updateDeposit (long id, long rate, String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        } else {
            Deposit deposit = depositRepository.findById(id).get();
            if (deposit == null) {
                throw new Exception("Deposit not found");
            }
            deposit.setRate(rate);
            depositRepository.save(deposit);
        }
    }

    public void deleteDeposit (long id, String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        } else {
            Deposit deposit = depositRepository.findById(id).get();
            if (deposit == null) {
                throw new Exception("Deposit not found");
            }
            depositRepository.deleteById(id);
        }
    }

    public Deposit makeDeposit(User user, long money, String startDate, long rate, int duration, String endDate, long totalMoney) throws Exception {
        // create a new deposit object and set its properties
        Deposit deposit = new Deposit(user, money,startDate, rate, duration, endDate, totalMoney);
        // save the deposit to the database
        depositRepository.save(deposit);
        return deposit;
    }

    public Deposit findDepositById(long id) throws Exception {
        Deposit deposit = depositRepository.findById(id).get();
        if (deposit == null) {
            throw new Exception("Deposit not found");
        }
        return deposit;
    }

    public Deposit findDepositByUserId(long id) throws Exception {
        Deposit deposit = depositRepository.findByUserId(id);
        if (deposit == null) {
            throw new Exception("Deposit not found");
        }
        return deposit;
    }

    public List<Deposit> findAllDeposit(String jwt) throws Exception {
        if (!userService.isAdmin(jwt)) {
            throw new Exception("You are not admin");
        } else {
            return depositRepository.findAll();
        }
    }

    public boolean hasDeposit(User user) throws Exception {
        List<Deposit> deposit = depositRepository.findByUser(user);
        if (deposit == null) {
            return false;
        }
        return true;
    }

    public List<Deposit> findDepositByUser(User user) throws Exception {
        List<Deposit> deposit = depositRepository.findByUser(user);
        if (deposit == null) {
            throw new Exception("Deposit not found");
        }
        return deposit;
    }

    public List<Deposit> findDepositByUserId(Long userId) throws Exception {
        User user = userRepository.findById(userId).get();
        List<Deposit> deposit = depositRepository.findByUser(user);
        return deposit;
    }

    public void takeMoney(long id) throws Exception {
        Deposit deposit = depositRepository.findById(id).get();
        deposit.setMoney(0);
        depositRepository.save(deposit);
    }
}
