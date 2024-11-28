package com.example.financialapi.service;

import com.example.financialapi.dao.UserDAO;
import com.example.financialapi.exception.BusinessException;
import com.example.financialapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createUser(User user) {
        if (user.getIdade() < 18) {
            throw new BusinessException("Usuário deve ter 18 anos ou mais.");
        }
        if (userDAO.findByCpf(user.getCpf()).isPresent()) {
            throw new BusinessException("CPF já cadastrado.");
        }
        user.setNumeroConta(generateAccountNumber());
        user.setSaldo(BigDecimal.ZERO);
        return userDAO.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userDAO.findById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }


    public void updateUserBalance(String numeroConta, BigDecimal novoSaldo) {
        userDAO.updateSaldo(numeroConta, novoSaldo);
    }

    private String generateAccountNumber() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}

