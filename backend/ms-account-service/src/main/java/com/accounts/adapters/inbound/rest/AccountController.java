package com.accounts.adapters.inbound.rest;

import com.accounts.adapters.inbound.rest.dto.*;
import com.accounts.ports.inbound.CreateAccountUseCase;
import com.accounts.ports.inbound.UpdateBalanceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final CreateAccountUseCase createUseCase;
    private final UpdateBalanceUseCase updateBalanceUseCase;

    public AccountController(CreateAccountUseCase createUseCase, UpdateBalanceUseCase updateBalanceUseCase) {
        this.createUseCase = createUseCase;
        this.updateBalanceUseCase = updateBalanceUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> create(@RequestBody AccountRequestDto dto) {
        return ResponseEntity.ok(AccountResponseDto.fromDomain(createUseCase.createAccount(dto.getOwnerId(), dto.getInitialBalance())));
    }

    @PostMapping("/{accountNumber}/debit")
    public ResponseEntity<Boolean> debit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        boolean success = updateBalanceUseCase.debit(accountNumber, amount);
        return ResponseEntity.ok(success);
    }

    @PostMapping("/{accountNumber}/credit")
    public ResponseEntity<Boolean> credit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        boolean success = updateBalanceUseCase.credit(accountNumber, amount);
        return ResponseEntity.ok(success);
    }
}