package com.project.se2project.repository;

import com.project.se2project.model.Transaction;
import com.project.se2project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByFromUserId(Long fromUserId);

    List<Transaction> findAllByToUserId(long toUserId);

    Transaction findByTransactionId(long transactionId);

    List<Transaction> findAllByAmount(int amount);
}
