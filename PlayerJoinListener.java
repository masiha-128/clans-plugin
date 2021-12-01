package com.russiankingdom2.clans.clansplugin.listeners;

import com.russiankingdom2.clans.clansplugin.ClansPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ClansPlugin.config.writePlayerData(e.getPlayer().getDisplayName(),"null");
    }
}
