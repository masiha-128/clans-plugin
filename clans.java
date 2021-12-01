package com.russiankingdom2.clans.clansplugin.commands;

import com.russiankingdom2.clans.clansplugin.ClansPlugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class clans implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "(!) you must be a player to use that command!");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("clans")) {
            Inventory gui = Bukkit.createInventory(player, 9,  ChatColor.BLACK + "clans menu");
            ItemStack close = new ItemStack(Material.BARRIER, 1);
            ItemMeta close_meta = close.getItemMeta();
            assert close_meta != null;
            close_meta.setDisplayName("Exit");
            close.setItemMeta(close_meta);

            ItemStack join;
            if (Objects.equals(ClansPlugin.config.readPlayerData(player.getDisplayName()), "null")) {
                join = new ItemStack(Material.WHITE_BANNER, 1);
                ItemMeta join_meta = join.getItemMeta();
                assert join_meta != null;
                join_meta.setDisplayName("join a clan");
                join.setItemMeta(join_meta);
            } else {
                join = new ItemStack(Material.RED_BANNER, 1);
                ItemMeta join_meta = join.getItemMeta();
                assert join_meta != null;
                join_meta.setDisplayName("you cannot leave a clan");
                join.setItemMeta(join_meta);
            }

            gui.setItem(8, close);
            gui.setItem(0, join);
            player.openInventory(gui);
            return true;
        } else if (command.getName().equalsIgnoreCase("war")) {
            player.teleport(new Location(Bukkit.getWorld("world"),Integer.parseInt(ClansPlugin.config.load("war_x")),Integer.parseInt(ClansPlugin.config.load("war_y")),Integer.parseInt(ClansPlugin.config.load("war_z"))));
        }
        return true;
    }
}
