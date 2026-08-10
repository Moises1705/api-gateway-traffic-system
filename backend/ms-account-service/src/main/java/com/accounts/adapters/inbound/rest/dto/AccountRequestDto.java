package com.accounts.adapters.inbound.rest.dto;

import java.math.BigDecimal;

public class AccountRequestDto {
    private String ownerId;
    private BigDecimal initialBalance;

    // Getters y Setters
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}