package com.transactions.adapters.outbound.client;

import com.transactions.ports.outbound.AccountClientPort;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class AccountWebClientAdapter implements AccountClientPort {

    private final WebClient webClient;

    public AccountWebClientAdapter(WebClient.Builder webClientBuilder) {
        // Se conecta al ms-account-service en el puerto 8082
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    @Override
    public boolean validateAndDebit(String accountNumber, BigDecimal amount) {
        try {
            // Aquí puedes conectar posteriormente con el endpoint de cuentas para actualizar saldo
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean credit(String accountNumber, BigDecimal amount) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}