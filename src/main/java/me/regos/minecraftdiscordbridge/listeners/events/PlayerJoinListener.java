package me.regos.minecraftdiscordbridge.listeners.events;

import me.regos.minecraftdiscordbridge.Minecraft_Discord_bridge;
import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.BaseListener;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends BaseListener {

    public PlayerJoinListener(Minecraft_Discord_bridge plugin, ToDiscordController controller) {
        super(plugin, controller);
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        String minecraftMessage = PlainTextComponentSerializer.plainText().serialize(e.joinMessage());
        String fullMessage = escapeMarkdown(minecraftMessage);
        controller.distribute(fullMessage);
    }
}
