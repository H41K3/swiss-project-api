package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BalanceResponseDTO;
import com.example.demo.dto.TransactionRequestDTO;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionType;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // Método para buscar por ID e disparar o erro caso não exista
    public Transaction getTransactionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID: " + id));
    }

    public Transaction createTransaction(TransactionRequestDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setDescription(dto.description());
        transaction.setAmount(dto.amount());
        transaction.setTransactionDate(dto.transactionDate());
        transaction.setType(dto.type()); 

        return repository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        // Verifica se existe antes de deletar para disparar o erro caso não exista
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar. Transação não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }

    public Transaction updateTransaction(Long id, TransactionRequestDTO dto) {
        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID: " + id));

        existingTransaction.setDescription(dto.description());
        existingTransaction.setAmount(dto.amount());
        existingTransaction.setTransactionDate(dto.transactionDate());
        existingTransaction.setType(dto.type()); 

        return repository.save(existingTransaction);
    }

    public BalanceResponseDTO getBalanceSummary() {
        List<Transaction> transactions = repository.findAll();
        
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.INCOME) {
                totalIncome = totalIncome.add(t.getAmount());
            } else if (t.getType() == TransactionType.EXPENSE) {
                totalExpense = totalExpense.add(t.getAmount());
            }
        }

        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new BalanceResponseDTO(totalIncome, totalExpense, balance);
    }
}