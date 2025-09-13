package me.regos.minecraftdiscordbridge.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiscordListeners extends ListenerAdapter {

    private final List<String> channelIDs;

    public DiscordListeners(List<String> channelIDs) {
        this.channelIDs = channelIDs;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!channelIDs.contains(event.getChannel().getId())) return;
        if (event.getAuthor() == event.getJDA().getSelfUser()) return;

        String name;
        if (event.isFromGuild() && event.getMember() != null) {
            name = event.getMember().getEffectiveName();
        } else {
            name = event.getAuthor().getEffectiveName();
        }

        String readMessage = "[Discord] <" + name + "> " + event.getMessage().getContentRaw();
        net.kyori.adventure.text.Component message = net.kyori.adventure.text.Component.text(readMessage);
        Bukkit.getServer().broadcast(message);
    }

}
