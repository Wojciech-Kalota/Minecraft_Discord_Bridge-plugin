package me.regos.minecraftdiscordbridge;

import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.events.ChatEventListener;
import me.regos.minecraftdiscordbridge.listeners.events.PlayerDeathListener;
import me.regos.minecraftdiscordbridge.listeners.events.PlayerJoinListener;
import me.regos.minecraftdiscordbridge.listeners.events.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Minecraft_Discord_bridge extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        List<String> webhookURLS = getConfig().getStringList("webhook-urls");

        ToDiscordController controller = new ToDiscordController(webhookURLS);
        getServer().getPluginManager().registerEvents(new ChatEventListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this, controller), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
