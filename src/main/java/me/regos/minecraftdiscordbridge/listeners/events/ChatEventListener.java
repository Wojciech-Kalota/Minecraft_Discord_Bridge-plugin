package me.regos.minecraftdiscordbridge.listeners.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.regos.minecraftdiscordbridge.Minecraft_Discord_bridge;
import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.BaseListener;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;


public class ChatEventListener extends BaseListener {

    public ChatEventListener(Minecraft_Discord_bridge plugin, ToDiscordController controller) {
        super(plugin, controller);
    }

    @EventHandler
    public void onChatMessage(AsyncChatEvent e) {
        String playerName = PlainTextComponentSerializer.plainText().serialize(e.getPlayer().displayName());
        playerName = escapeMarkdown(playerName);
        String playerMessage = PlainTextComponentSerializer.plainText().serialize(e.originalMessage());
        String minecraftMessage = "<" + playerName + "> " + playerMessage;
        String fullMessage = minecraftMessage;

        controller.distribute(fullMessage);
    }
}