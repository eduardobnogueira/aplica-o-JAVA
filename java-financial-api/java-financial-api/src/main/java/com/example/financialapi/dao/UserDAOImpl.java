package com.example.financialapi.dao;

import com.example.financialapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (nome, idade, cpf, numero_conta, saldo) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getNome(), user.getIdade(), user.getCpf(), user.getNumeroConta(), user.getSaldo());
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findByNumeroConta(String numeroConta) {
        String sql = "SELECT * FROM users WHERE numero_conta = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), numeroConta);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        String sql = "SELECT * FROM users WHERE cpf = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), cpf);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public void updateSaldo(String numeroConta, BigDecimal novoSaldo) {
        String sql = "UPDATE users SET saldo = ? WHERE numero_conta = ?";
        jdbcTemplate.update(sql, novoSaldo, numeroConta);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setNome(rs.getString("nome"));
            user.setIdade(rs.getInt("idade"));
            user.setCpf(rs.getString("cpf"));
            user.setNumeroConta(rs.getString("numero_conta"));
            user.setSaldo(rs.getBigDecimal("saldo"));
            return user;
        }
    }
}

