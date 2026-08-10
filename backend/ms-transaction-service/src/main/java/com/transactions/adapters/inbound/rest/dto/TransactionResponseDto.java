package com.transactions.adapters.inbound.rest.dto;

import com.transactions.domain.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TransactionResponseDto {
    private Long id;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;
}