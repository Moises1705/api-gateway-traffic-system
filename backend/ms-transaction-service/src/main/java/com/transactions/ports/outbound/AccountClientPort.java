package com.transactions.ports.outbound;

import java.math.BigDecimal;

public interface AccountClientPort {
    boolean validateAndDebit(String accountNumber, BigDecimal amount);

    boolean credit(String accountNumber, BigDecimal amount);
}