package com.project.se2project.repository;

import com.project.se2project.model.Transaction;
import com.project.se2project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
