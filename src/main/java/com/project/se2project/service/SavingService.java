package com.project.se2project.service;

import com.project.se2project.model.Saving;
import com.project.se2project.model.User;
import com.project.se2project.repository.SavingRepository;
import com.project.se2project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingService {

    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    public Saving makeSaving(User user, long money, String startDate, long rate , String nextIncomeDate) throws Exception {
        // create a new saving object and set its properties
        Saving saving = new Saving(user, money, startDate, rate);
        // save the saving to the database
        savingRepository.save(saving);
        return saving;
    }

    public void updateSaving (long id, long rate, String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        } else {
            Saving saving = savingRepository.findById(id).get();
            if (saving == null) {
                throw new Exception("Saving not found");
            }
            saving.setRate(rate);
            savingRepository.save(saving);
        }
    }

    public void deleteSaving (long id, String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        } else {
            Saving saving = savingRepository.findById(id).get();
            if (saving == null) {
                throw new Exception("Saving not found");
            }
            savingRepository.deleteById(id);
        }
    }

    public List<Saving> findSavingByUserId(long id) throws Exception {
        User user = userRepository.findById(id).get();
        if(user == null){
            throw new Exception("User not found");
        }
        List<Saving> saving = savingRepository.findByUser(user);
        if(saving == null){
            throw new Exception("User does not have any saving account");
        }
        return saving;
    }

    public List<Saving> findAllSaving(String jwt) throws Exception {
        if (!userService.isAdminJwtCheck(jwt)) {
            throw new Exception("You are not admin");
        }else {
            List<Saving> saving = savingRepository.findAll();
            if (saving == null) {
                throw new Exception("No saving account found");
            }return saving;
        }
    }

    public Saving takeMoneyFromSaving(long id, long amount) throws Exception{
        Saving saving = savingRepository.findById(id).get();
        if(saving == null){
            throw new Exception("Saving not found");
        }
        saving.setMoney(saving.getMoney() - amount);
        savingRepository.save(saving);
        return saving;
    }
}
