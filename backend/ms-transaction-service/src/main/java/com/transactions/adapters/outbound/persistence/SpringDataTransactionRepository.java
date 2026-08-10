package com.transactions.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionJpaEntity, Long> {
}