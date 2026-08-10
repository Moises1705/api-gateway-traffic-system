package com.transactions.adapters.outbound.persistence;

import com.transactions.domain.Transaction;
import com.transactions.ports.outbound.TransactionRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final SpringDataTransactionRepository repository;

    public TransactionRepositoryAdapter(SpringDataTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionJpaEntity entity = new TransactionJpaEntity(
                transaction.getId(),
                transaction.getSourceAccountNumber(),
                transaction.getTargetAccountNumber(),
                transaction.getAmount(),
                transaction.getStatus(),
                transaction.getCreatedAt()
        );
        TransactionJpaEntity saved = repository.save(entity);
        return new Transaction(
                saved.getId(),
                saved.getSourceAccountNumber(),
                saved.getTargetAccountNumber(),
                saved.getAmount(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return repository.findById(id).map(entity -> new Transaction(
                entity.getId(),
                entity.getSourceAccountNumber(),
                entity.getTargetAccountNumber(),
                entity.getAmount(),
                entity.getStatus(),
                entity.getCreatedAt()
        ));
    }
}