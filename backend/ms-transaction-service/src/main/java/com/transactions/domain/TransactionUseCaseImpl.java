package com.transactions.domain;

import com.transactions.ports.inbound.ExecuteTransferUseCase;
import com.transactions.ports.outbound.AccountClientPort;
import com.transactions.ports.outbound.TransactionRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionUseCaseImpl implements ExecuteTransferUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountClientPort accountClientPort;

    public TransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort, AccountClientPort accountClientPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountClientPort = accountClientPort;
    }

    @Override
    public Transaction transfer(String sourceAccount, String targetAccount, BigDecimal amount) {
        // 1. Validar y debitar cuenta origen mediante WebClient (ms-account-service)
        boolean debited = accountClientPort.validateAndDebit(sourceAccount, amount);

        if (!debited) {
            Transaction failed = new Transaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            return transactionRepositoryPort.save(failed);
        }

        // 2. Acreditar cuenta destino
        boolean credited = accountClientPort.credit(targetAccount, amount);

        if (!credited) {
            Transaction failed = new Transaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            return transactionRepositoryPort.save(failed);
        }

        // 3. Registrar transacción exitosa
        Transaction success = new Transaction(sourceAccount, targetAccount, amount, TransactionStatus.COMPLETED);
        return transactionRepositoryPort.save(success);
    }
}