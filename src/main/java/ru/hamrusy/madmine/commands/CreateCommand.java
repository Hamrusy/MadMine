package ru.hamrusy.madmine.commands;

import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Main;
import ru.hamrusy.madmine.utils.Utils;
import ru.hamrusy.madmine.mines.Mines;

public class CreateCommand extends Sub {
    public CreateCommand() {
    }

    public boolean execute(Player player, String[] array) {
        if (array.length < 2) {
            return false;
        } else {
            Selection selection = Main.getWorldEdit().getSelection(player);
            if (selection == null) {
                Utils.sendMessage(player, Utils.getMessage("create.no_selection"));
                return true;
            } else if (Mines.getMine(array[1]) != null) {
                Utils.sendMessage(player, Utils.getMessage("create.already"));
                return true;
            } else {
                Mines.saveMine(array[1], selection);
                Utils.sendMessage(player, Utils.getMessage("create.complete"));
                return true;
            }
        }
    }

    public String command() {
        return "create";
    }

    public String permission() {
        return "mmine.command.create";
    }

    public String description() {
        return Utils.getMessage("create.usage");
    }

    public String[] aliases() {
        return new String[]{"c"};
    }
}
