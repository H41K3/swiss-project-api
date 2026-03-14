package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.TransactionRequestDTO;
import com.example.demo.model.Transaction;
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

    public Transaction createTransaction(TransactionRequestDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setDescription(dto.description());
        transaction.setAmount(dto.amount());
        transaction.setTransactionDate(dto.transactionDate());
        
        // NOVO: Passando o tipo do DTO para a Entidade
        transaction.setType(dto.type()); 

        return repository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }

    public Transaction updateTransaction(Long id, TransactionRequestDTO dto) {
        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada com o ID: " + id));

        existingTransaction.setDescription(dto.description());
        existingTransaction.setAmount(dto.amount());
        existingTransaction.setTransactionDate(dto.transactionDate());
        
        // NOVO: Atualizando o tipo também
        existingTransaction.setType(dto.type()); 

        return repository.save(existingTransaction);
    }

    // NOVO MÉTODO: O calculador de saldo
    public com.example.demo.dto.BalanceResponseDTO getBalanceSummary() {
        List<Transaction> transactions = repository.findAll();
        
        java.math.BigDecimal totalIncome = java.math.BigDecimal.ZERO;
        java.math.BigDecimal totalExpense = java.math.BigDecimal.ZERO;

        // O Java vai olhar transação por transação e separar as caixinhas
        for (Transaction t : transactions) {
            if (t.getType() == com.example.demo.model.TransactionType.INCOME) {
                totalIncome = totalIncome.add(t.getAmount());
            } else if (t.getType() == com.example.demo.model.TransactionType.EXPENSE) {
                totalExpense = totalExpense.add(t.getAmount());
            }
        }

        // Saldo = Receitas - Despesas
        java.math.BigDecimal balance = totalIncome.subtract(totalExpense);

        return new com.example.demo.dto.BalanceResponseDTO(totalIncome, totalExpense, balance);
    }
}