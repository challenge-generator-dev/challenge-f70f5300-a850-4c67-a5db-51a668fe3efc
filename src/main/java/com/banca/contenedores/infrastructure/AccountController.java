package com.banca.contenedores.infrastructure;


import com.banca.contenedores.domain.AccountStatus;
import com.banca.contenedores.application.AccountService;
import com.banca.contenedores.domain.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public record CreateAccountRequest(
        @NotBlank(message = "El número de cuenta es obligatorio")
        String accountNumber,
        
        @NotBlank(message = "El titular de la cuenta es obligatorio")
        String accountHolder,
        
        @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
        BigDecimal initialBalance
    ) {}

    public record TransactionRequest(
        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
        BigDecimal amount
    ) {}

    public record TransferRequest(
        @NotNull(message = "La cuenta de destino es obligatoria")
        UUID toAccountId,
        
        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
        BigDecimal amount
    ) {}

    public record AccountResponse(
        UUID id,
        String accountNumber,
        String accountHolder,
        BigDecimal balance,
        String status,
        String createdAt,
        String updatedAt
    ) {
        public static AccountResponse fromDomain(Account account) {
            return new AccountResponse(
                account.id(),
                account.accountNumber(),
                account.accountHolder(),
                account.balance(),
                account.status().name(),
                account.createdAt().toString(),
                account.updatedAt().toString()
            );
        }
    }

    public record ApiResponse<T>(
        boolean success,
        String message,
        T data
    ) {
        public static <T> ApiResponse<T> ok(T data, String message) {
            return new ApiResponse<>(true, message, data);
        }

        public static <T> ApiResponse<T> error(String message) {
            return new ApiResponse<>(false, message, null);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {
        logger.info("POST /api/v1/accounts - Creando cuenta: {}", request.accountNumber());
        
        Account account = accountService.createAccount(
            request.accountNumber(),
            request.accountHolder(),
            request.initialBalance()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.ok(AccountResponse.fromDomain(account), "Cuenta creada exitosamente"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getAllAccounts() {
        logger.info("GET /api/v1/accounts - Obteniendo todas las cuentas");
        
        List<Account> accounts = accountService.getAllAccounts();
        List<AccountResponse> response = accounts.stream()
            .map(AccountResponse::fromDomain)
            .toList();
        
        return ResponseEntity.ok(ApiResponse.ok(response, "Cuentas obtenidas exitosamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccountById(@PathVariable UUID id) {
        logger.info("GET /api/v1/accounts/{} - Obteniendo cuenta por ID", id);
        
        return accountService.getAccountById(id)
            .map(account -> ResponseEntity.ok(
                ApiResponse.ok(AccountResponse.fromDomain(account), "Cuenta encontrada")))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Cuenta no encontrada: " + id)));
    }

    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccountByNumber(
            @PathVariable String accountNumber) {
        logger.info("GET /api/v1/accounts/number/{} - Obteniendo cuenta por número", accountNumber);
        
        return accountService.getAccountByNumber(accountNumber)
            .map(account -> ResponseEntity.ok(
                ApiResponse.ok(AccountResponse.fromDomain(account), "Cuenta encontrada")))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Cuenta no encontrada: " + accountNumber)));
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<ApiResponse<AccountResponse>> deposit(
            @PathVariable UUID id,
            @Valid @RequestBody TransactionRequest request) {
        logger.info("POST /api/v1/accounts/{}/deposit - Depósito de {}", id, request.amount());
        
        Account account = accountService.deposit(id, request.amount());
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Depósito realizado exitosamente"));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<ApiResponse<AccountResponse>> withdraw(
            @PathVariable UUID id,
            @Valid @RequestBody TransactionRequest request) {
        logger.info("POST /api/v1/accounts/{}/withdraw - Retiro de {}", id, request.amount());
        
        Account account = accountService.withdraw(id, request.amount());
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Retiro realizado exitosamente"));
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<ApiResponse<AccountResponse>> transfer(
            @PathVariable UUID id,
            @Valid @RequestBody TransferRequest request) {
        logger.info("POST /api/v1/accounts/{}/transfer - Transferencia de {} a {}", 
            id, request.amount(), request.toAccountId());
        
        Account account = accountService.transfer(id, request.toAccountId(), request.amount());
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Transferencia realizada exitosamente"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AccountResponse>> changeStatus(
            @PathVariable UUID id,
            @RequestParam Account.AccountStatus status) {
        logger.info("PATCH /api/v1/accounts/{}/status - Cambiando estado a {}", id, status);
        
        Account account = accountService.changeAccountStatus(id, status);
        
        return ResponseEntity.ok(
            ApiResponse.ok(AccountResponse.fromDomain(account), "Estado actualizado exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable UUID id) {
        logger.info("DELETE /api/v1/accounts/{} - Eliminando cuenta", id);
        
        accountService.deleteAccount(id);
        
        return ResponseEntity.ok(ApiResponse.ok(null, "Cuenta eliminada exitosamente"));
    }
}