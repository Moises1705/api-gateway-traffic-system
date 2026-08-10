package com.accounts.domain;

import com.accounts.ports.inbound.CreateAccountUseCase;
import com.accounts.ports.outbound.AccountRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    public AccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Account createAccount(String ownerId, BigDecimal initialBalance) {
        AccountNumber accountNumber = new AccountNumber(UUID.randomUUID().toString());
        Account account = new Account(accountNumber, ownerId, initialBalance);
        return accountRepositoryPort.save(account);
    }
}