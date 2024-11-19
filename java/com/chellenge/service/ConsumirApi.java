package com.challenge.chellenge.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumirApi {

    public String obterDados(String endereco) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 301 || response.statusCode() == 302) {
            URI newUri = response.headers().firstValue("Location").map(URI::create).orElse(null);
            if (newUri != null) {
                request = HttpRequest.newBuilder()
                        .uri(newUri)
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } else {
                throw new RuntimeException("Redirecionamento sem URL de destino");
            }
        }

        // Verifique o código de status da resposta final
        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na requisição: " + response.statusCode());
        }
        String json = response.body();
        return json;
    }
}
