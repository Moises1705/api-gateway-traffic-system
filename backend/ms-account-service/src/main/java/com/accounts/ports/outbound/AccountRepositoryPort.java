package com.accounts.ports.outbound;

import com.accounts.domain.Account;
import com.accounts.domain.AccountNumber;

import java.util.Optional;

public interface AccountRepositoryPort {
    Account save(Account account);

    Optional<Account> findByAccountNumber(AccountNumber accountNumber);

    Optional<Account> findById(Long id);
}