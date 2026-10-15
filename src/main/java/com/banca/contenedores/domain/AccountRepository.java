package com.banca.contenedores.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);
    
    Optional<Account> findById(UUID id);
    
    Optional<Account> findByAccountNumber(String accountNumber);
    
    List<Account> findAll();
    
    void deleteById(UUID id);
}