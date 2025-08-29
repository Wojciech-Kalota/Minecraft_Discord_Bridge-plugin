package me.regos.minecraftdiscordbridge.listeners;

import me.regos.minecraftdiscordbridge.Minecraft_Discord_bridge;
import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import org.bukkit.event.Listener;


public class BaseListener implements Listener {
    protected final Minecraft_Discord_bridge plugin;
    protected final ToDiscordController controller;

    public BaseListener(Minecraft_Discord_bridge plugin, ToDiscordController controller){
        this.controller = controller;
        this.plugin = plugin;
    }

    protected String escapeMarkdown(String text) {
        return text
                .replace("*", "\\*")
                .replace("_", "\\_")
                .replace("~", "\\~")
                .replace("`", "\\`")
                .replace("|", "\\|");
    }
}
