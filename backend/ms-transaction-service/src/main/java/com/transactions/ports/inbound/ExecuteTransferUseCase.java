package com.transactions.ports.inbound;

import com.transactions.domain.Transaction;

import java.math.BigDecimal;

public interface ExecuteTransferUseCase {
    Transaction transfer(String sourceAccountNumber, String targetAccountNumber, BigDecimal amount);
}