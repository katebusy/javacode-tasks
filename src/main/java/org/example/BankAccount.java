package org.example;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccount {
    private UUID accountId;
    private BigDecimal balance;

    public BankAccount(BigDecimal balance) {
        this.balance = balance;
        this.accountId = UUID.randomUUID();
    }

    public synchronized void deposit(BigDecimal depositSum) {
        balance = balance.add(depositSum);
    }

    public synchronized void withdraw(BigDecimal withdrawSum) {
        if (balance.compareTo(withdrawSum) >= 0) {
            balance = balance.subtract(withdrawSum);
        } else {
            throw new IllegalArgumentException("Insufficient funds!");
        }
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public UUID getAccountId() {
        return accountId;
    }
}
