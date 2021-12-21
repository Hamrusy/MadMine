package ru.hamrusy.madmine.mines;

import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Config;
import ru.hamrusy.madmine.Money;

public class MinePlayer extends PlaceholderHook {
    private static FileConfiguration config;

    public MinePlayer() {
    }

    public String onRequest(OfflinePlayer offlinePlayer, String s) {
        return offlinePlayer != null && offlinePlayer.isOnline() ? this.onPlaceholderRequest(offlinePlayer.getPlayer(), s) : null;
    }

    public String onPlaceholderRequest(Player player, String s) {
        return s.equals("salary") ? Money.getSalaryFormat(player) : String.valueOf(get(player, s));
    }

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("players.yml"));
    }

    public static void saveConfig() {
        Config.save(getConfig(), "players.yml");
    }

    public static void resetOres(Player player) {
        getConfig().set(player.getName(), (Object)null);
        saveConfig();
    }

    public static int get(Player player, String s) {
        if (getConfig().getString(player.getName()) == null) {
            getConfig().set(player.getName() + "." + s, 0);
            saveConfig();
        }

        return getConfig().getInt(player.getName() + "." + s);
    }

    public static void set(Player player, String s, int n) {
        getConfig().set(player.getName() + "." + s, n);
        saveConfig();
    }

    public static void add(Player player, String s, int n) {
        getConfig().set(player.getName() + "." + s, get(player, s) + n);
        saveConfig();
    }
}
