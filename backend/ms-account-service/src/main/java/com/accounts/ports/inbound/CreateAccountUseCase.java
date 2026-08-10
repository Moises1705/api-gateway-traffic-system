package com.accounts.ports.inbound;

import com.accounts.domain.Account;

import java.math.BigDecimal;

public interface CreateAccountUseCase {
    Account createAccount(String ownerId, BigDecimal initialBalance);
}