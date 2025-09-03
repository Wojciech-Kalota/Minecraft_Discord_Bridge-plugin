package me.regos.minecraftdiscordbridge.controllers;

//import com.google.gson.Gson;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.List;

public class ToDiscordController {

    //private static final Gson gson = new Gson();
    //private final HttpClient client = HttpClient.newHttpClient();
    private final List<String> channelIDs;
    private final JDA jda;

    public ToDiscordController(List<String> channelIDs, JDA jda) {
        this.channelIDs = channelIDs;
        this.jda = jda;
    }
    
    public void distribute(String fullMessage){
        for (String id: channelIDs) {
            forwardMessage(fullMessage, id);
        }
    }

    public void forwardMessage(String fullMessage, String channelID) {
        TextChannel channel = jda.getTextChannelById(channelID);

        if (channel != null) {
            channel.sendMessage(fullMessage).queue();
        } else {
            System.out.println("Channel " + channelID + " not found");
        }
    }

//    public void sendMessage(String fullMessage, String webhookURL) {
//
//        String jsonPayload = gson.toJson(Collections.singletonMap("content", fullMessage));
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(webhookURL))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
//                .build();
//
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenAccept(response -> {
//                    if (response.statusCode() != 204) {
//                        System.out.println("[DiscordMinecraftBridge] Discord webhook failed: " + response.statusCode() + " - " + response.body());
//                    }
//                })
//                .exceptionally(ex -> {
//                    System.err.println("[DiscordMinecraftBridge] Exception while sending webhook: " + ex.getMessage());
//                    return null;
//                });
//    }

}
