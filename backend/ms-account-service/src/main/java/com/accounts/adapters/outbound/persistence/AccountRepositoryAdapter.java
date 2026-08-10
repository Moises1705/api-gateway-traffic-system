package com.accounts.adapters.outbound.persistence;

import com.accounts.domain.Account;
import com.accounts.domain.AccountNumber;
import com.accounts.ports.outbound.AccountRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final SpringDataAccountRepository repository;

    public AccountRepositoryAdapter(SpringDataAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        AccountJpaEntity entity = toJpaEntity(account);
        AccountJpaEntity savedEntity = repository.save(entity);
        return toDomainEntity(savedEntity);
    }

    @Override
    public Optional<Account> findByAccountNumber(AccountNumber accountNumber) {
        return repository.findByAccountNumber(accountNumber.getValue())
                .map(this::toDomainEntity);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id)
                .map(this::toDomainEntity);
    }

    // Mappers internos
    private AccountJpaEntity toJpaEntity(Account account) {
        return new AccountJpaEntity(
                account.getId(),
                account.getAccountNumber().getValue(),
                account.getOwnerId(),
                account.getBalance()
        );
    }

    private Account toDomainEntity(AccountJpaEntity entity) {
        return new Account(
                entity.getId(),
                new AccountNumber(entity.getAccountNumber()),
                entity.getOwnerId(),
                entity.getBalance()
        );
    }
}