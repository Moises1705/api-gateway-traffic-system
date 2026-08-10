package com.transactions.ports.outbound;

import com.transactions.domain.Transaction;

import java.util.Optional;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long id);
}