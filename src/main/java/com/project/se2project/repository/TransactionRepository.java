package com.project.se2project.repository;

import com.project.se2project.model.Transaction;
import com.project.se2project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByFromUserId(Long fromUserId);
}
