package com.example.financialapi.dao;

import com.example.financialapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByNumeroConta(String numeroConta);
    Optional<User> findByCpf(String cpf);
    List<User> findAll();
    void updateSaldo(String numeroConta, BigDecimal novoSaldo);
}

