package ru.hamrusy.madmine.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Utils;

public class CommandManager implements CommandExecutor {
    private List<Sub> commands;

    public CommandManager() {
        (this.commands = new ArrayList()).add(new CreateCommand());
        this.commands.add(new RemoveCommand());
        this.commands.add(new ListCommand());
        this.commands.add(new ReloadCommand());
        this.commands.add(new SalaryCommand());
        this.commands.add(new ResetCommand());
        this.commands.add(new AddNpc());
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] array) {
        if (commandSender instanceof Player) {
            Player player = (Player)commandSender;
            if (array.length == 0) {
                if (!this.getAllowCommands(player).isEmpty()) {
                    Utils.sendMessage(player, Utils.getMessage("commandmanager.title_help"));
                    Iterator iterator = this.getAllowCommands(player).iterator();

                    while(iterator.hasNext()) {
                        Utils.sendMessage(player, ((Sub)iterator.next()).description());
                    }
                }

                return true;
            }

            Sub command2 = this.getCommand(array[0]);
            if (command2 != null) {
                if (command2.permission() != null && !player.hasPermission(command2.permission())) {
                    Utils.sendMessage(player, Utils.getMessage("commandmanager.no_permission"));
                    return true;
                }

                try {
                    if (!command2.execute(player, array)) {
                        Utils.sendMessage(player, command2.description());
                    }
                } catch (Exception var8) {
                    var8.printStackTrace();
                    Utils.sendMessage(player, Utils.getMessage("commandmanager.error"));
                }
            } else {
                Utils.sendMessage(player, Utils.getMessage("commandmanager.command_not_found"));
            }
        }

        return true;
    }

    public List<Sub> getAllowCommands(Player player) {
        ArrayList<Sub> list = new ArrayList();
        Iterator var3 = this.commands.iterator();

        while(var3.hasNext()) {
            Sub sub = (Sub)var3.next();
            if (sub.permission() != null) {
                if (player.hasPermission(sub.permission())) {
                    list.add(sub);
                }
            } else {
                list.add(sub);
            }
        }

        return list;
    }

    public Sub getCommand(String s) {
        Iterator var2 = this.commands.iterator();

        while(true) {
            Sub sub;
            do {
                if (!var2.hasNext()) {
                    return null;
                }

                sub = (Sub)var2.next();
                if (sub.command().equalsIgnoreCase(s)) {
                    return sub;
                }
            } while(sub.aliases() == null);

            String[] array = sub.aliases();
            int length = array.length;

            for(int i = 0; i < length; ++i) {
                if (array[i].equalsIgnoreCase(s)) {
                    return sub;
                }
            }
        }
    }
}
