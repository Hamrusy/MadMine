package ru.hamrusy.madmine.commands;

import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Money;
import ru.hamrusy.madmine.Utils;
import ru.hamrusy.madmine.mines.MinePlayer;

public class SalaryCommand extends Sub {
    public SalaryCommand() {
    }

    public boolean execute(Player player, String[] array) {
        if (Money.getSalary(player) <= 0.0D) {
            Utils.sendMessage(player, Utils.getMessage("salary.zero"));
            return true;
        } else {
            Utils.sendMessage(player, Utils.getMessage("salary.complete").replace("%salary%", Money.getSalaryFormat(player)));
            Money.addMoney(player, Money.getSalary(player));
            Money.setSalary(player, 0.0D);
            MinePlayer.resetOres(player);
            return true;
        }
    }

    public String command() {
        return "salary";
    }

    public String permission() {
        return "mmine.command.salary";
    }

    public String description() {
        return Utils.getMessage("salary.usage");
    }

    public String[] aliases() {
        return null;
    }
}
