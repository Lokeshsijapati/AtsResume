package com.example.Ats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroqService {

        private final WebClient webClient;

        public GroqService(WebClient webClient) {
                this.webClient = webClient;
        }

        @Value("${groq.api.key}")
        private String apiKey;

        @Value("${groq.api.url}")
        private String apiUrl;

        public String generateResponse(String prompt) {

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put(
                                "model",
                                "llama-3.3-70b-versatile");
                requestBody.put(
                                "messages",
                                List.of(
                                                Map.of(
                                                                "role", "system",
                                                                "content",
                                                                "You are an ATS resume analysis assistant. Always return structured professional output."),
                                                Map.of(
                                                                "role", "user",
                                                                "content", prompt)));

                return webClient.post()
                                .uri(apiUrl)
                                .header("Authorization", "Bearer " + apiKey)
                                .header("Content-Type", "application/json")
                                .bodyValue(requestBody)
                                .retrieve()
                                .onStatus(
                                                status -> status.isError(),
                                                response -> response.bodyToMono(String.class)
                                                                .map(body -> new RuntimeException(
                                                                                "Groq Error: " + body)))
                                .bodyToMono(String.class)
                                .block();
        }
}