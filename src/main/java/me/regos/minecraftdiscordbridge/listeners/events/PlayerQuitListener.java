package me.regos.minecraftdiscordbridge.listeners.events;

import me.regos.minecraftdiscordbridge.Minecraft_Discord_bridge;
import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.BaseListener;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends BaseListener {

    public PlayerQuitListener(Minecraft_Discord_bridge plugin, ToDiscordController controller) {
        super(plugin, controller);
    }

    @EventHandler
    public void OnQuitJoin(PlayerQuitEvent e) {
        String minecraftMessage = PlainTextComponentSerializer.plainText().serialize(e.quitMessage());
        String fullMessage = escapeMarkdown(minecraftMessage);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            controller.distribute(fullMessage);
        });
    }
}
