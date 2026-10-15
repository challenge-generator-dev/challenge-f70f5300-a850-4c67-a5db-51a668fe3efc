package com.banca.contenedores.application;


import com.banca.contenedores.domain.AccountStatus;
import com.banca.contenedores.domain.Account;
import com.banca.contenedores.domain.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("100.00");
    private static final BigDecimal MAXIMUM_WITHDRAWAL = new BigDecimal("10000.00");

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String accountNumber, String accountHolder, BigDecimal initialBalance) {
        logger.info("Creando nueva cuenta: {} para titular: {}", accountNumber, accountHolder);

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío");
        }

        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("El titular de la cuenta no puede estar vacío");
        }

        Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountNumber);
        if (existingAccount.isPresent()) {
            throw new IllegalArgumentException("Ya existe una cuenta con el número: " + accountNumber);
        }

        Account newAccount = new Account(
            UUID.randomUUID(),
            accountNumber,
            accountHolder,
            initialBalance != null ? initialBalance : BigDecimal.ZERO,
            Account.AccountStatus.ACTIVE,
            java.time.LocalDateTime.now(),
            java.time.LocalDateTime.now()
        );

        Account savedAccount = accountRepository.save(newAccount);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.id());
        return savedAccount;
    }

    public Optional<Account> getAccountById(UUID id) {
        logger.debug("Buscando cuenta por ID: {}", id);
        return accountRepository.findById(id);
    }

    public Optional<Account> getAccountByNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public List<Account> getAllAccounts() {
        logger.debug("Obteniendo todas las cuentas");
        return accountRepository.findAll();
    }

    public Account deposit(UUID accountId, BigDecimal amount) {
        logger.info("Depósito de {} en cuenta {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero");
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (account.status() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede depositar en una cuenta con estado: " + account.status());
        }

        BigDecimal newBalance = account.balance().add(amount);
        Account updatedAccount = account.updateBalance(newBalance);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.balance());
        return accountRepository.save(updatedAccount);
    }

    public Account withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Retiro de {} de cuenta {}", amount, accountId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero");
        }

        if (amount.compareTo(MAXIMUM_WITHDRAWAL) > 0) {
            throw new IllegalArgumentException("El monto del retiro excede el límite máximo de: " + MAXIMUM_WITHDRAWAL);
        }

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (account.status() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException("No se puede retirar de una cuenta con estado: " + account.status());
        }

        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.compareTo(MINIMUM_BALANCE) < 0) {
            throw new IllegalStateException("Saldo insuficiente. Saldo mínimo requerido: " + MINIMUM_BALANCE);
        }

        Account updatedAccount = account.updateBalance(newBalance);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.balance());
        return accountRepository.save(updatedAccount);
    }

    public Account transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        logger.info("Transferencia de {} desde cuenta {} hacia cuenta {}", amount, fromAccountId, toAccountId);

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada: " + fromAccountId));

        Account toAccount = accountRepository.findById(toAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no encontrada: " + toAccountId));

        if (fromAccount.status() != Account.AccountStatus.ACTIVE || toAccount.status() != Account.AccountStatus.ACTIVE) {
            throw new IllegalStateException("Ambas cuentas deben estar activas para realizar la transferencia");
        }

        BigDecimal newFromBalance = fromAccount.balance().subtract(amount);
        if (newFromBalance.compareTo(MINIMUM_BALANCE) < 0) {
            throw new IllegalStateException("Saldo insuficiente en cuenta origen para realizar la transferencia");
        }

        Account updatedFromAccount = fromAccount.updateBalance(newFromBalance);
        Account updatedToAccount = toAccount.updateBalance(toAccount.balance().add(amount));

        accountRepository.save(updatedFromAccount);
        accountRepository.save(updatedToAccount);

        logger.info("Transferencia exitosa. Saldo origen: {}, Saldo destino: {}", 
            updatedFromAccount.balance(), updatedToAccount.balance());
        return updatedFromAccount;
    }

    public Account changeAccountStatus(UUID accountId, Account.AccountStatus newStatus) {
        logger.info("Cambiando estado de cuenta {} a {}", accountId, newStatus);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        Account updatedAccount = account.changeStatus(newStatus);
        logger.info("Estado de cuenta cambiado exitosamente a: {}", newStatus);
        return accountRepository.save(updatedAccount);
    }

    public void deleteAccount(UUID accountId) {
        logger.info("Eliminando cuenta: {}", accountId);

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountId));

        if (account.balance().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalStateException("No se puede eliminar una cuenta con saldo positivo");
        }

        accountRepository.deleteById(accountId);
        logger.info("Cuenta eliminada exitosamente: {}", accountId);
    }
}