package ru.hamrusy.madmine;

import java.util.Iterator;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Checks {
    public Checks() {
    }

    public static void check(Player player) {
        if (!player.hasPermission("mmine.checks")) {
            if ((player.getAllowFlight() || player.isFlying()) && Utils.getConfig().getBoolean("checks.fly.enable")) {
                Utils.sendMessage(player, Utils.getConfig().getString("checks.fly.message"));
                player.setAllowFlight(false);
                player.setFlying(false);
            }

            if (Utils.getConfig().getBoolean("checks.gamemode.enable")) {
                Iterator var1 = Utils.getConfig().getStringList("checks.gamemode.modes").iterator();

                while(var1.hasNext()) {
                    String s = (String)var1.next();
                    if (player.getGameMode() == GameMode.valueOf(s.toUpperCase())) {
                        player.setGameMode(GameMode.valueOf(Utils.getConfig().getString("checks.gamemode.setmode").toUpperCase()));
                        Utils.sendMessage(player, Utils.getConfig().getString("checks.gamemode.message"));
                    }
                }
            }

        }
    }
}