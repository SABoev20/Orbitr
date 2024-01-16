package com.backend.orbitr.bll.controllers;

import com.backend.orbitr.bll.services.AuthService;
import com.backend.orbitr.bll.services.OfferService;
import com.backend.orbitr.bll.services.TransactionService;
import com.backend.orbitr.dal.models.Offer;
import com.backend.orbitr.dal.models.Transaction;
import com.backend.orbitr.dal.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    private final AuthService authService;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(AuthService authService, TransactionService transactionService) {
        this.authService = authService;
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public List<Transaction> getOrderedTransactions(Authentication authentication) {
        User user = authService.getUserByToken(authentication);

        return transactionService.getAllTransactionsOrderedByDate(user.getID());
    }
}
