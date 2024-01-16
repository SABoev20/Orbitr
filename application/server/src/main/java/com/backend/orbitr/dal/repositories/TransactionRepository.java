package com.backend.orbitr.dal.repositories;

import com.backend.orbitr.dal.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserIdOrderByTransactionDateDesc(int userId);
}
