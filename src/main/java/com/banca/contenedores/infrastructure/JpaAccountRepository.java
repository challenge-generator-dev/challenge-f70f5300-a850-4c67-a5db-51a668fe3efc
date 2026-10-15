package com.banca.contenedores.infrastructure;


import com.banca.contenedores.domain.AccountStatus;
import com.banca.contenedores.domain.Account;
import com.banca.contenedores.domain.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID>, AccountRepository {
    @Override
    default Account save(Account account) {
        AccountEntity entity = AccountEntity.fromDomain(account);
        AccountEntity savedEntity = save(entity);
        return savedEntity.toDomain();
    }
    
    @Override
    default Optional<Account> findById(UUID id) {
        return findById(id).map(AccountEntity::toDomain);
    }
    
    @Override
    default Optional<Account> findByAccountNumber(String accountNumber) {
        return findByAccountNumber(accountNumber).map(AccountEntity::toDomain);
    }
    
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}

@Entity
@Table(name = "accounts")
class AccountEntity {
    @Id
    private UUID id;
    
    @Column(nullable = false, unique = true, length = 20)
    private String accountNumber;
    
    @Column(nullable = false, length = 50)
    private String accountHolder;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Account.AccountStatus status;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;

    public AccountEntity() {
    }

    public AccountEntity(UUID id, String accountNumber, String accountHolder, BigDecimal balance,
                        Account.AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AccountEntity fromDomain(Account account) {
        return new AccountEntity(
            account.id(),
            account.accountNumber(),
            account.accountHolder(),
            account.balance(),
            account.status(),
            account.createdAt(),
            account.updatedAt()
        );
    }

    public Account toDomain() {
        return new Account(
            id,
            accountNumber,
            accountHolder,
            balance,
            status,
            createdAt,
            updatedAt
        );
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account.AccountStatus getStatus() {
        return status;
    }

    public void setStatus(Account.AccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}