package com.russiankingdom2.clans.clansplugin;

import com.russiankingdom2.clans.clansplugin.commands.clans;
import com.russiankingdom2.clans.clansplugin.listeners.GUI;
import com.russiankingdom2.clans.clansplugin.listeners.PlayerJoinListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class ClansPlugin extends JavaPlugin {

    public static ClansPlugin instance;
    public static config_util config;

    @Override
    public void onEnable() {
        instance = this;
        config = new config_util();
        config.save_default_cfg(instance);

        Path path = Paths.get("./plugins/ClansPlugin/playerData/");
        try {
            Files.createDirectory(path);
        } catch (IOException ignored) {}

        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "SimpleClans is Starting...");
        Objects.requireNonNull(getCommand("clans")).setExecutor(new clans());
        Objects.requireNonNull(getCommand("war")).setExecutor(new clans());
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new GUI(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SimpleClans has Started!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "SimpleClans is stopping...");
    }
}
