package me.regos.minecraftdiscordbridge.controllers;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class ToDiscordController {

    private static final Gson gson = new Gson();
    private final HttpClient client = HttpClient.newHttpClient();
    private final List<String> webhookURLS;

    public ToDiscordController(List<String> webhookURLS) {
        this.webhookURLS = webhookURLS;
    }
    
    public void distribute(String fullMessage){
        for (String url: webhookURLS) {
            sendMessage(fullMessage, url);
        }
    }

    public void sendMessage(String fullMessage, String webhookURL) {

        String jsonPayload = gson.toJson(Collections.singletonMap("content", fullMessage));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webhookURL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() != 204) {
                        System.out.println("[DiscordMinecraftBridge] Discord webhook failed: " + response.statusCode() + " - " + response.body());
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("[DiscordMinecraftBridge] Exception while sending webhook: " + ex.getMessage());
                    return null;
                });
    }

}
