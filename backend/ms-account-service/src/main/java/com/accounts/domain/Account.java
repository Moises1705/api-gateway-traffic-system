package com.accounts.domain;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private final AccountNumber accountNumber;
    private final String ownerId; // ID del usuario asociado a la cuenta
    private BigDecimal balance;

    public Account(Long id, AccountNumber accountNumber, String ownerId, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.ownerId = ownerId;
        this.balance = balance != null ? balance : BigDecimal.ZERO;
    }

    public Account(AccountNumber accountNumber, String ownerId, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerId = ownerId;
        this.balance = initialBalance != null ? initialBalance : BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero.");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Fondos insuficientes en la cuenta.");
        }
        this.balance = this.balance.subtract(amount);
    }

    public Long getId() {
        return id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}