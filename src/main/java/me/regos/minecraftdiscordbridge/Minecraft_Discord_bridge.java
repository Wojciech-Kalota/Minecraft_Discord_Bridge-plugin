package me.regos.minecraftdiscordbridge;

import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.DiscordListeners;
import me.regos.minecraftdiscordbridge.listeners.events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.logging.Level;

public final class Minecraft_Discord_bridge extends JavaPlugin {

    private ToDiscordController controller;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        List<String> channelIDs = getConfig().getStringList("channel-ids");
        String token = getConfig().getString("token");

        // Initialize discord bot
        this.getLogger().log(Level.INFO, "[DiscordMinecraftBridge] Discord bot is being initialized");
        try {
            JDA jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .setActivity(Activity.playing("Minecraft"))
                    .addEventListeners(new DiscordListeners(channelIDs))
                    .build();

            jda.awaitReady();
            this.getLogger().log(Level.INFO, "[DiscordMinecraftBridge] Discord bot initialized");
            controller = new ToDiscordController(channelIDs, jda);

        } catch (Exception e) {
            this.getLogger().log(java.util.logging.Level.SEVERE, "Failed to initialize Discord bot", e);
        }

        // Register minecraft listeners
        getServer().getPluginManager().registerEvents(new ChatEventListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerAdvancementDoneListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new CommandListener(this, controller), this);

    }

}
