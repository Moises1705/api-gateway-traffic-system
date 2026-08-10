package com.transactions.adapters.inbound.rest;

import com.transactions.adapters.inbound.rest.dto.TransactionResponseDto;
import com.transactions.adapters.inbound.rest.dto.TransferRequestDto;
import com.transactions.domain.Transaction;
import com.transactions.ports.inbound.ExecuteTransferUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final ExecuteTransferUseCase executeTransferUseCase;

    public TransactionController(ExecuteTransferUseCase executeTransferUseCase) {
        this.executeTransferUseCase = executeTransferUseCase;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDto> transfer(@RequestBody @Valid TransferRequestDto request) {
        Transaction transaction = executeTransferUseCase.transfer(
                request.getSourceAccountNumber(),
                request.getTargetAccountNumber(),
                request.getAmount()
        );

        TransactionResponseDto response = new TransactionResponseDto(
                transaction.getId(),
                transaction.getSourceAccountNumber(),
                transaction.getTargetAccountNumber(),
                transaction.getAmount(),
                transaction.getStatus(),
                transaction.getCreatedAt()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}