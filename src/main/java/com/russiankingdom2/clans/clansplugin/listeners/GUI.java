package com.russiankingdom2.clans.clansplugin.listeners;

import com.russiankingdom2.clans.clansplugin.ClansPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;
import java.util.Random;

public class GUI implements Listener {
    @EventHandler
    public void onGUIClick(InventoryClickEvent event) {

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.BLACK + "clans menu")) {

            switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
                case BARRIER:
                    event.getWhoClicked().closeInventory();
                    break;
                case WHITE_BANNER:
                    Random random = new Random();

                    int count = 6;

                    if (ClansPlugin.config.load("clan_1").equalsIgnoreCase("null")) {
                        count -=1 ;
                    }
                    if (ClansPlugin.config.load("clan_2").equalsIgnoreCase("null")) {
                        count -=1 ;
                    }
                    if (ClansPlugin.config.load("clan_3").equalsIgnoreCase("null")) {
                        count -=1 ;
                    }
                    if (ClansPlugin.config.load("clan_4").equalsIgnoreCase("null")) {
                        count -=1 ;
                    }
                    if (ClansPlugin.config.load("clan_5").equalsIgnoreCase("null")) {
                        count -=1 ;
                    }

                    String team = "no team";
                    int num_team = random.nextInt(count);
                    StringBuilder builder = new StringBuilder();

                    switch (String.valueOf(num_team)) {
                        case "1":
                            team = ClansPlugin.config.load("clan_1");
                            break;
                        case "2":
                            team = ClansPlugin.config.load("clan_2");
                            break;
                        case "3":
                            team = ClansPlugin.config.load("clan_3");
                            break;
                        case "4":
                            team = ClansPlugin.config.load("clan_4");
                            break;
                        case "5":
                            team = ClansPlugin.config.load("clan_5");
                            break;
                    }

                    ClansPlugin.config.writePlayerData(event.getWhoClicked().getName(), team);
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage("cool your in the team : " + team);

                    builder.append("clan_");
                    builder.append(num_team);

                    if (ClansPlugin.config.load("auto_team").equalsIgnoreCase("true")) {
                        try {
                            Scoreboard sb = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
                            Objects.requireNonNull(sb.getTeam(ClansPlugin.config.load(builder.toString()))).addEntry(event.getWhoClicked().getName());
                        } catch (Exception e) {
                            ClansPlugin.instance.getServer().getConsoleSender().sendMessage("team didn't exist! creating one...");
                            Scoreboard sb = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
                            sb.registerNewTeam(ClansPlugin.config.load(builder.toString()));
                            Objects.requireNonNull(sb.getTeam(ClansPlugin.config.load(builder.toString()))).addEntry(event.getWhoClicked().getName());
                        }
                    }

                    break;
                case RED_BANNER:
                    event.getWhoClicked().closeInventory();
                    event.getWhoClicked().sendMessage("you cannot do that!");
                    break;
            }

            event.setCancelled(true);
        }
    }
}
