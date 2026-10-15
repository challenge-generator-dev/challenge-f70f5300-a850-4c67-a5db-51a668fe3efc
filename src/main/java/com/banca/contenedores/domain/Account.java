package com.banca.contenedores.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Account(
    @NotNull
    UUID id,

    @NotNull
    @Size(min = 5, max = 20)
    String accountNumber,

    @NotNull
    @Size(min = 3, max = 50)
    String accountHolder,

    @NotNull
    @PositiveOrZero
    BigDecimal balance,

    @NotNull
    AccountStatus status,

    @NotNull
    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    public Account {
        if (balance == null) {
            balance = BigDecimal.ZERO;
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = AccountStatus.ACTIVE;
        }
    }

    public Account updateBalance(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account must be active to update balance");
        }
        BigDecimal newBalance = balance.add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        return new Account(id, accountNumber, accountHolder, newBalance, status, createdAt, LocalDateTime.now());
    }

    public Account changeStatus(AccountStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return new Account(id, accountNumber, accountHolder, balance, newStatus, createdAt, LocalDateTime.now());
    }
}