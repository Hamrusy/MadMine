package ru.hamrusy.madmine.commands;

import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Utils;
import ru.hamrusy.madmine.mines.MineManager;

public class ResetCommand extends Sub {
    public ResetCommand() {
    }

    public boolean execute(Player player, String[] array) {
        MineManager.reset();
        Utils.sendMessage(player, Utils.getMessage("reset.complete"));
        return true;
    }

    public String command() {
        return "reset";
    }

    public String permission() {
        return "mmine.command.reset";
    }

    public String description() {
        return Utils.getMessage("reset.usage");
    }

    public String[] aliases() {
        return null;
    }
}