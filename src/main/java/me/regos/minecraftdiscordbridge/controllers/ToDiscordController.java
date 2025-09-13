package me.regos.minecraftdiscordbridge.controllers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.List;

public class ToDiscordController {

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

}
