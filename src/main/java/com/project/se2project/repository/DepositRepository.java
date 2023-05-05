package com.project.se2project.repository;

import com.project.se2project.model.Deposit;
import com.project.se2project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

    List<Deposit> findByUser(User user);

    Deposit findByUserId(long id);
}
