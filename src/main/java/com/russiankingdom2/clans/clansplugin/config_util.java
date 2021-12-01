package com.russiankingdom2.clans.clansplugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class config_util {

    FileConfiguration config;

    public void save_default_cfg(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public String load(String name) {
        return config.getString(name);
    }

    public void writePlayerData(String playerName, String Data) {
        try {
            createFile("./plugins/ClansPlugin/playerData/" + playerName + ".txt");
            FileWriter myWriter = new FileWriter("./plugins/ClansPlugin/playerData/" + playerName + ".txt");
            myWriter.write(Data);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readPlayerData(String playerName) {
                // variable declaration
                int ch;
                StringBuilder sb = new StringBuilder();
                // check if File exists or not
                FileReader fr=null;
                try
                {
                    fr = new FileReader("./plugins/ClansPlugin/playerData/" + playerName + ".txt");
                }
                catch (FileNotFoundException fe)
                {
                    System.out.println("File not found");
                }

                try {
                    // read from FileReader till the end of file
                    while (true) {
                        assert fr != null;
                        if ((ch = fr.read()) == -1) break;
                        sb.append((char) ch);
                    }

                    // close the file
                    fr.close();
                    return sb.toString();
                } catch (Exception ignored) {return null;}
    }

    private void createFile(String playerName) {
        try {
            File myObj = new File(playerName);
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
