package me.regos.minecraftdiscordbridge.listeners.events;

import me.regos.minecraftdiscordbridge.Minecraft_Discord_bridge;
import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.BaseListener;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.regex.Matcher;

public class CommandListener extends BaseListener {

    public CommandListener(Minecraft_Discord_bridge plugin, ToDiscordController controller) {
        super(plugin, controller);
    }


    @EventHandler
    public void OnServerCommand(ServerCommandEvent e) {
        String command = e.getCommand();

        if (command.startsWith("/say")) {
            String fullMessage = command.replaceFirst("/say",  "[Server]");
            controller.distribute(fullMessage);
        }
    }

    @EventHandler
    public void OnPlayerCommand(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage();
        String playerName = PlainTextComponentSerializer.plainText().serialize(e.getPlayer().displayName());
            playerName = escapeMarkdown(playerName);

            if (command.startsWith("/say")) {
                String fullMessage = command.replaceFirst("/say",  Matcher.quoteReplacement("[" + playerName + "]"));
                controller.distribute(fullMessage);
        }
    }
}
