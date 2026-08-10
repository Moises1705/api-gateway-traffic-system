package com.accounts.adapters.inbound.rest.dto;

import com.accounts.domain.Account;

import java.math.BigDecimal;

public class AccountResponseDto {
    private String accountNumber;
    private String ownerId;
    private BigDecimal balance;

    public static AccountResponseDto fromDomain(Account account) {
        AccountResponseDto dto = new AccountResponseDto();
        dto.accountNumber = account.getAccountNumber().getValue();
        dto.ownerId = account.getOwnerId();
        dto.balance = account.getBalance();
        return dto;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}