package ru.hamrusy.madmine.commands;

import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Utils;

public class ReloadCommand extends Sub {
    public ReloadCommand() {
    }

    public boolean execute(Player player, String[] array) {
        if (array.length < 1) {
            return false;
        } else {
          Utils.reloadConfig();
          Utils.sendMessage(player, Utils.getMessage("reload.complete"));
          return true;
        }
    }

    public String command() {
        return "reload";
    }

    public String permission() {
        return "mmine.command.reload";
    }

    public String description() {
        return Utils.getMessage("reload.usage");
    }

    public String[] aliases() {
        return null;
    }
}
