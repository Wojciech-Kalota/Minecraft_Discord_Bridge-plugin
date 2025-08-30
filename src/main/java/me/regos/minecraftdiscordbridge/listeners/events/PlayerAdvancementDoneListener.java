package me.regos.minecraftdiscordbridge.listeners.events;

import me.regos.minecraftdiscordbridge.Minecraft_Discord_bridge;
import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.BaseListener;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class PlayerAdvancementDoneListener extends BaseListener {

    public PlayerAdvancementDoneListener(Minecraft_Discord_bridge plugin, ToDiscordController controller) {
        super(plugin, controller);
    }

    @EventHandler
    public void OnAdvancementDone(PlayerAdvancementDoneEvent e) {
        if (e.message() != null) {
            String minecraftMessage = PlainTextComponentSerializer.plainText().serialize(e.message());
            String fullMessage = escapeMarkdown(minecraftMessage);
//        String s2 = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().displayName());
//        String s3 = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().getRoot().displayName());
//        String s4 = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().getDisplay().displayName());
//        String s5 = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().getDisplay().description());
//        String s6 = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().getDisplay().title());
//        String s7 = String.valueOf(e.getAdvancement().getDisplay().doesAnnounceToChat());
//        String s8 = String.valueOf(e.getAdvancement().getDisplay().doesShowToast());
//        String s9 = String.valueOf(e.getAdvancement().getDisplay().isHidden());
//        String s10 = PlainTextComponentSerializer.plainText().serialize(e.getPlayer().displayName());

            controller.distribute(fullMessage);
        }
    }
}
