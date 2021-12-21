package ru.hamrusy.madmine.commands;

import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Utils;
import ru.hamrusy.madmine.mines.MineManager;
import ru.hamrusy.madmine.mines.Mines;

public class RemoveCommand extends Sub {
    public RemoveCommand() {
    }

    public boolean execute(Player player, String[] array) {
        if (array.length < 2) {
            return false;
        } else {
            String s = array[1];
            if (Mines.getMine(s) == null) {
                Utils.sendMessage(player, Utils.getMessage("remove.not-found"));
                return true;
            } else {
                MineManager.reset();
                Mines.removeMine(s);
                Utils.sendMessage(player, Utils.getMessage("remove.complete"));
                return true;
            }
        }
    }

    public String command() {
        return "remove";
    }

    public String permission() {
        return "mmine.command.remove";
    }

    public String description() {
        return Utils.getMessage("remove.usage");
    }

    public String[] aliases() {
        return null;
    }
}
