package com.accounts.domain;

import com.accounts.ports.inbound.CreateAccountUseCase;
import com.accounts.ports.inbound.UpdateBalanceUseCase;
import com.accounts.ports.outbound.AccountRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountUseCaseImpl implements CreateAccountUseCase, UpdateBalanceUseCase {

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

    @Override
    public boolean debit(String accountNumber, BigDecimal amount) {
        Optional<Account> optAccount = accountRepositoryPort.findByAccountNumber(new AccountNumber(accountNumber));
        if (optAccount.isPresent()) {
            Account account = optAccount.get();
            try {
                account.withdraw(amount);
                accountRepositoryPort.save(account);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean credit(String accountNumber, BigDecimal amount) {
        Optional<Account> optAccount = accountRepositoryPort.findByAccountNumber(new AccountNumber(accountNumber));
        if (optAccount.isPresent()) {
            Account account = optAccount.get();
            account.deposit(amount);
            accountRepositoryPort.save(account);
            return true;
        }
        return false;
    }
}