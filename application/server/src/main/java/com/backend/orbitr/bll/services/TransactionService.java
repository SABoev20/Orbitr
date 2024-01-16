package com.backend.orbitr.bll.services;

import com.backend.orbitr.dal.models.Transaction;
import com.backend.orbitr.dal.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionsOrderedByDate(int userId){
        return transactionRepository.findAllByUserIdOrderByTransactionDateDesc(userId);
    }
}
