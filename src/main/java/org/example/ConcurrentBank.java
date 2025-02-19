package org.example;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {
    private Map<UUID, BankAccount> accounts;

    public ConcurrentBank() {
        this.accounts = new ConcurrentHashMap<>();
    }

    public BankAccount createAccount(BigDecimal balance) {
        BankAccount account = new BankAccount(balance);
        accounts.put(account.getAccountId(), account);
        return account;
    }

    public void transfer(BankAccount account1, BankAccount account2, BigDecimal sum) {
        synchronized (account1.getAccountId().compareTo(account2.getAccountId()) < 0
                ? account1
                : account2) {
            account1.withdraw(sum);
            account2.deposit(sum);
        }
    }

    public synchronized BigDecimal getTotalBalance() {
        return accounts.values().stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
