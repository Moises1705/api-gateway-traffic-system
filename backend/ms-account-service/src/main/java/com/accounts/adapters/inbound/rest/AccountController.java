package com.accounts.adapters.inbound.rest;

import com.accounts.adapters.inbound.rest.dto.*;
import com.accounts.ports.inbound.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final CreateAccountUseCase createUseCase;

    public AccountController(CreateAccountUseCase createUseCase) {
        this.createUseCase = createUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> create(@RequestBody AccountRequestDto dto) {
        return ResponseEntity.ok(AccountResponseDto.fromDomain(createUseCase.createAccount(dto.getOwnerId(), dto.getInitialBalance())));
    }
}