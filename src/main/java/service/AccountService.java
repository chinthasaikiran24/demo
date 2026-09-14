package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Entity.Account;
import Repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void transferMoney(
            Long fromId,
            Long toId,
            double amount) {

        Account from = repository.findById(fromId)
                .orElseThrow(() ->
                        new RuntimeException("Sender account not found"));

        Account to = repository.findById(toId)
                .orElseThrow(() ->
                        new RuntimeException("Receiver account not found"));

        // Business Validation
        if (amount <= 0) {
            throw new RuntimeException(
                    "Transfer amount must be greater than zero");
        }

        // Insufficient balance
        if (from.getBalance() < amount) {
            throw new RuntimeException(
                    "Insufficient balance");
        }

        // Withdraw
        from.setBalance(
                from.getBalance() - amount);

        // Deposit
        to.setBalance(
                to.getBalance() + amount);

        repository.save(from);
        repository.save(to);
    }
}