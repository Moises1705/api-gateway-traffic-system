package com.accounts.ports.inbound;

import com.accounts.domain.Account;

import java.util.Optional;

public interface GetAccountUseCase {
    Optional<Account> getAccountByNumber(String accountNumber);

    Optional<Account> getAccountById(Long id);
}