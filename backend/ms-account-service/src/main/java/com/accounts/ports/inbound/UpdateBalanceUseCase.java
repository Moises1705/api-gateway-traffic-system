package com.accounts.ports.inbound;

import java.math.BigDecimal;

public interface UpdateBalanceUseCase {
    boolean debit(String accountNumber, BigDecimal amount);

    boolean credit(String accountNumber, BigDecimal amount);
}