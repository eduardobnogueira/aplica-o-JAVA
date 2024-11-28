package com.example.financialapi.controller;

import com.example.financialapi.model.Transaction;
import com.example.financialapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> transferMoney(@RequestBody Transaction transaction) {
        transactionService.transferMoney(transaction);
        return ResponseEntity.ok("Transferência realizada com sucesso.");
    }
}

