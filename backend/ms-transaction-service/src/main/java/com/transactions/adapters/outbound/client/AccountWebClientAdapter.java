package com.transactions.adapters.outbound.client;

import com.transactions.ports.outbound.AccountClientPort;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class AccountWebClientAdapter implements AccountClientPort {

    private final WebClient webClient;

    public AccountWebClientAdapter(WebClient.Builder webClientBuilder) {
        // Apunta al ms-account-service
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    @Override
    public boolean validateAndDebit(String accountNumber, BigDecimal amount) {
        try {
            Boolean response = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/api/accounts/{accountNumber}/debit")
                            .queryParam("amount", amount)
                            .build(accountNumber))
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block(); // Bloqueante para mantener la transacción sincrónica (o manejar con Reactive si prefieres)

            return response != null && response;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean credit(String accountNumber, BigDecimal amount) {
        try {
            Boolean response = webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/api/accounts/{accountNumber}/credit")
                            .queryParam("amount", amount)
                            .build(accountNumber))
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            return response != null && response;
        } catch (Exception e) {
            return false;
        }
    }
}