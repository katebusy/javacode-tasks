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

    public synchronized void transfer(BankAccount account1, BankAccount account2, BigDecimal sum) {
        BigDecimal startingBalance1 = account1.getBalance();
        BigDecimal startingBalance2 = account2.getBalance();
        if (startingBalance1.compareTo(sum) >= 0) {
            try {
                account1.withdraw(sum);
                account2.deposit(sum);
            } catch (Exception e) {
                account1.deposit(startingBalance1.subtract(sum));
                account2.withdraw(startingBalance2.subtract(sum));
                throw new RuntimeException(e);
            }

        }
    }

    public synchronized BigDecimal getTotalBalance() {
        return accounts.values().stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
