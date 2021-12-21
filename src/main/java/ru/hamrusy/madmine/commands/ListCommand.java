package ru.hamrusy.madmine.commands;

import java.util.Iterator;
import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Utils;
import ru.hamrusy.madmine.mines.MineInfo;
import ru.hamrusy.madmine.mines.Mines;

public class ListCommand extends Sub {
    public ListCommand() {
    }

    public boolean execute(Player player, String[] array) {
        if (Mines.getMines().isEmpty()) {
            Utils.sendMessage(player, Utils.getMessage("list.empty"));
            return true;
        } else {
            GUI.openGUI(player);
            Utils.sendMessage(player, Utils.getMessage("list.title"));
            int n = 0;
            Iterator var4 = Mines.getMines().iterator();

            while(var4.hasNext()) {
                MineInfo mineInfo = (MineInfo)var4.next();
                ++n;
                Utils.sendMessage(player, Utils.getMessage("list.format").replace("%step%", "" + n).replace("%name%", mineInfo.getName()).replace("%stats%", Utils.getMessage("list.stats").replace("%min%", (int)mineInfo.getMin().getX() + ", " + (int)mineInfo.getMin().getY() + ", " + (int)mineInfo.getMin().getZ()).replace("%max%", (int)mineInfo.getMax().getX() + ", " + (int)mineInfo.getMax().getY() + ", " + (int)mineInfo.getMax().getZ()).replace("%world%", mineInfo.getWorld().getName())));
            }

            return true;
        }
    }

    public String command() {
        return "list";
    }

    public String permission() {
        return "mmine.command.list";
    }

    public String description() {
        return Utils.getMessage("list.usage");
    }

    public String[] aliases() {
        return null;
    }
}
