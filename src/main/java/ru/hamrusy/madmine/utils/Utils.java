package ru.hamrusy.madmine.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Main;


public class Utils {
    private static FileConfiguration config;
    private static FileConfiguration messages;

    public Utils() {
    }

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("config.yml"));
    }

    public static FileConfiguration getMessages() {
        return messages  != null ? messages  : (messages  = Config.getFile("messages.yml"));
    }

    public static void reloadConfig() {
        Bukkit.getPluginManager().disablePlugin(Main.getInstance());
        Bukkit.getPluginManager().enablePlugin(Main.getInstance());
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String getMessage(String s) {
        return getMessages().getString("messages." + s);
    }

    public static String getNpc(String s) {
        return getConfig().getString("npc." + s);
    }

    public static void sendMessage(Player player, String s) {
        if (s != null) {
            String[] var2 = s.split(";");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String s2 = var2[var4];
                if (s2.startsWith("actionbar:")) {
                    ActionBar.sendActionBar(player, s2.split("actionbar:")[1]);
                } else if (s2.startsWith("title:")) {
                    Title.sendTitle(player, s2.split("title:")[1]);
                } else if (!s2.isEmpty()) {
                    player.sendMessage(color(getMessage("prefix") + s2));
                }
            }
        }

    }
}
