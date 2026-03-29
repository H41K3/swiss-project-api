package com.globalwallet.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globalwallet.api.model.Transaction;
import com.globalwallet.api.model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    // O Spring Boot é inteligente o suficiente para ler esse nome e montar o "SELECT * FROM transactions WHERE user_id = ?" sozinho!
    List<Transaction> findAllByUser(User user);
}