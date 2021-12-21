package ru.hamrusy.madmine.commands;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import ru.hamrusy.madmine.Utils;

public class AddNpc extends Sub {
    public AddNpc() {
    }

    public boolean execute(Player player, String[] array) {
        if (array.length < 1) {
            return false;
        } else {
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, Utils.getNpc("name"));
            npc.spawn(Bukkit.getPlayer(player.getName()).getLocation());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc select " + Utils.getNpc("name"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc skin " + Utils.getNpc("skin"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc cmdadd mine salary");
            if (Utils.getConfig().getBoolean("npc.look")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "npc look");
            } else {
                return false;
            }
            Utils.sendMessage(player, Utils.getMessage("setnpc.complete"));
            return true;
        }
    }

    public String command() {
        return "setnpc";
    }

    public String permission() {
        return "mmine.command.setnpc";
    }

    public String description() {
        return Utils.getMessage("setnpc.usage");
    }

    public String[] aliases() {
        return null;
    }
}

