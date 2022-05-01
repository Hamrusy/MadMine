package ru.hamrusy.madmine.mines;

import com.google.common.collect.Maps;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.hamrusy.madmine.utils.Config;
import ru.hamrusy.madmine.utils.Money;

import java.util.HashMap;
import java.util.Map;

public class MinePlayer extends PlaceholderHook {
    private static FileConfiguration config;
    private static FileConfiguration settings;

    public MinePlayer() {
    }

    public static int getBackpack(final Player player) {
        if (!getConfig().contains(player.getName() + ".backpack")) {
            resetBackpack(player);
        }
        return getConfig().getInt(player.getName() + ".backpack");
    }

    public static Map<String, String> getData(final Player player) {
        final HashMap hashMap = Maps.newHashMap();
        for (final String s : getSettings().getConfigurationSection("ores").getKeys(false)) {
            hashMap.put(s, String.valueOf(getConfig().getInt(player.getName() + "." + s)));
        }
        hashMap.put("backpackMax", String.valueOf(getBackpackMax(player)));
        hashMap.put("backpackCurrent", String.valueOf(getBackpack(player)));
        final double booster = getBooster(player);
        final int n = (int)Math.ceil(Money.getSalary(player));
        hashMap.put("booster", String.valueOf(booster));
        hashMap.put("salary", String.valueOf(n));
        return (Map<String, String>)hashMap;
    }

    public static int getBackpackMax(final Player player) {
        final ConfigurationSection configurationSection = getSettings().getConfigurationSection("groups");
        int int1 = configurationSection.getInt("default.backpack");
        for (final String s : configurationSection.getKeys(false)) {
            final int int2 = configurationSection.getInt(s + ".backpack");
            if (player.hasPermission("group." + s) && int2 > int1) {
                int1 = int2;
            }
        }
        return int1;
    }

    public static void increaseBackpack(final Player player) {
        getConfig().set(player.getName() + ".backpack", getBackpack(player) + 1);
    }

    public static void resetBackpack(final Player player) {
        getConfig().set(player.getName() + ".backpack", 0);
    }

    public static double getBooster(final Player player) {
        final ConfigurationSection configurationSection = getSettings().getConfigurationSection("groups");
        double double1 = configurationSection.getDouble("default.booster");
        for (final String s : configurationSection.getKeys(false)) {
            final double double2 = configurationSection.getDouble(s + ".booster");
            if (player.hasPermission("group." + s) && double2 > double1) {
                double1 = double2;
            }
        }
        return double1;
    }

    public String onRequest(OfflinePlayer offlinePlayer, String s) {
        return offlinePlayer != null && offlinePlayer.isOnline() ? this.onPlaceholderRequest(offlinePlayer.getPlayer(), s) : null;
    }

    public String onPlaceholderRequest(Player player, String s) {
        return s.equals("salary") ? Money.getSalaryFormat(player) : String.valueOf(get(player, s));
    }

    public static FileConfiguration getSettings() {
        return settings != null ? settings : (settings = Config.getFile("config.yml"));
    }

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("players.yml"));
    }

    public static void saveConfig() {
        Config.save(getConfig(), "players.yml");
    }

    public static void resetOres(Player player) {
        getConfig().set(player.getName(), null);
        saveConfig();
        resetBackpack(player);
    }

    public static int get(Player player, String s) {
        if (getConfig().getString(player.getName()) == null) {
            getConfig().set(player.getName() + "." + s, 0);
            saveConfig();
        }

        return getConfig().getInt(player.getName() + "." + s);
    }

    public static void add(Player player, String s, int n) {
        getConfig().set(player.getName() + "." + s, get(player, s) + n);
        saveConfig();
        increaseBackpack(player);
    }
}
