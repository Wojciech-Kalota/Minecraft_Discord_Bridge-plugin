package me.regos.minecraftdiscordbridge;

import me.regos.minecraftdiscordbridge.controllers.ToDiscordController;
import me.regos.minecraftdiscordbridge.listeners.DiscordListeners;
import me.regos.minecraftdiscordbridge.listeners.events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public final class Minecraft_Discord_bridge extends JavaPlugin {

    private ToDiscordController controller;
    private JDA jda;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        List<String> channelIDs = getConfig().getStringList("channel-ids");
        String token = getConfig().getString("token");

        // Initialize discord bot
        System.out.println("[DiscordMinecraftBridge] Discord bot is being initialized");
        //Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                jda = JDABuilder.createDefault(token)
                        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                        .setActivity(Activity.playing("Minecraft"))
                        .addEventListeners(new DiscordListeners(channelIDs))
                        .build();

                jda.awaitReady(); // block until ready
                System.out.println("[DiscordMinecraftBridge] Discord bot initialized");
                controller = new ToDiscordController(channelIDs, jda);

            } catch (Exception e) {
                this.getLogger().log(java.util.logging.Level.SEVERE, "Failed to initialize Discord bot", e);
            }
        //});

        // Register minecraft listeners
        getServer().getPluginManager().registerEvents(new ChatEventListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new PlayerAdvancementDoneListener(this, controller), this);
        getServer().getPluginManager().registerEvents(new CommandListener(this, controller), this);

    }


    @Override
    public void onDisable() {

    }
}
