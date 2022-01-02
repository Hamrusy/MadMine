package ru.hamrusy.madmine.utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Money {
    private static Economy economy;
    private static HashMap<Player, Double> salary = new HashMap();

    public Money() {
        economy = (Economy)Bukkit.getServicesManager().getRegistration(Economy.class).getProvider();
    }

    public static void addMoney(Player player, double n) {
        economy.depositPlayer(player, n);
    }

    public static void addSalary(Player player, double n) {
        if (salary.get(player) == null) {
            salary.put(player, 0.0D);
        }

        salary.put(player, (Double)salary.get(player) + n);
    }

    public static void takeSalary(Player player, double n) {
        if (salary.get(player) == null) {
            salary.put(player, 0.0D);
        }

        salary.put(player, (Double)salary.get(player) - n);
        if (getSalary(player) < 0.0D) {
            salary.put(player, 0.0D);
        }

    }

    public static void setSalary(Player player, double n) {
        salary.put(player, n);
    }

    public static double getSalary(Player player) {
        if (salary.get(player) == null) {
            salary.put(player, 0.0D);
        }

        return (Double)salary.get(player);
    }

    public static String getSalaryFormat(Player player) {
        return (new DecimalFormat("#0.00")).format(getSalary(player));
    }
}
