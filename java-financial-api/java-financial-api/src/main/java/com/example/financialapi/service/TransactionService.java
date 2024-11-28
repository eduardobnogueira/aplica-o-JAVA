package com.example.financialapi.service;

import com.example.financialapi.dao.UserDAO;
import com.example.financialapi.exception.BusinessException;
import com.example.financialapi.model.Transaction;
import com.example.financialapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final UserDAO userDAO;

    @Autowired
    public TransactionService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void transferMoney(Transaction transaction) {
        User origem = userDAO.findByNumeroConta(transaction.getContaOrigem())
                .orElseThrow(() -> new BusinessException("Conta de origem não encontrada."));
        User destino = userDAO.findByNumeroConta(transaction.getContaDestino())
                .orElseThrow(() -> new BusinessException("Conta de destino não encontrada."));

        if (origem.getSaldo().compareTo(transaction.getValor()) < 0) {
            throw new BusinessException("Saldo insuficiente para a transação.");
        }

        BigDecimal novoSaldoOrigem = origem.getSaldo().subtract(transaction.getValor());
        BigDecimal novoSaldoDestino = destino.getSaldo().add(transaction.getValor());

        userDAO.updateSaldo(origem.getNumeroConta(), novoSaldoOrigem);
        userDAO.updateSaldo(destino.getNumeroConta(), novoSaldoDestino);
    }
}

