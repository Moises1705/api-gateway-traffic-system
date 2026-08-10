package com.transactions.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public Transaction(String sourceAccountNumber, String targetAccountNumber, BigDecimal amount, TransactionStatus status) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.amount = amount;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Transaction(Long id, String sourceAccountNumber, String targetAccountNumber, BigDecimal amount, TransactionStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}