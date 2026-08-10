package com.transactions.adapters.inbound.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequestDto {
    @NotBlank
    private String sourceAccountNumber;
    @NotBlank
    private String targetAccountNumber;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;
}