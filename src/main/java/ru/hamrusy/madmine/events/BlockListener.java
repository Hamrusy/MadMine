package ru.hamrusy.madmine.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.hamrusy.madmine.utils.Money;
import ru.hamrusy.madmine.utils.Utils;
import ru.hamrusy.madmine.mines.MineBlock;
import ru.hamrusy.madmine.mines.MineManager;
import ru.hamrusy.madmine.mines.MinePlayer;
import ru.hamrusy.madmine.mines.Mines;

public class BlockListener implements Listener {
    public BlockListener() {
    }

    @EventHandler
    public void onBreak(BlockBreakEvent blockBreakEvent) {
        if (Mines.getMine(blockBreakEvent.getBlock().getLocation())) {
            if (MineManager.getBlock(blockBreakEvent.getBlock().getLocation()) != null && blockBreakEvent.getBlock().getType() == Material.getMaterial(Utils.getConfig().getString("setblock").toUpperCase())) {
                blockBreakEvent.setCancelled(true);
            } else if (Mines.getBlock(blockBreakEvent.getBlock().getType()) == null) {
                if (!blockBreakEvent.getPlayer().hasPermission("umine.breakblocks") && Utils.getConfig().getBoolean("block-break.enable")) {
                    Utils.sendMessage(blockBreakEvent.getPlayer(), Utils.getConfig().getString("block-break.message"));
                    blockBreakEvent.setCancelled(true);
                }

            } else {
                if (Utils.getConfig().getBoolean("resoures.straight")) {
                    blockBreakEvent.getBlock().getDrops().clear();
                }

                blockBreakEvent.setDropItems(Utils.getConfig().getBoolean("resoures.drop"));
                if (!Utils.getConfig().getBoolean("exp")) {
                    blockBreakEvent.setExpToDrop(0);
                }

                MineBlock block = Mines.getBlock(blockBreakEvent.getBlock().getType());
                Utils.sendMessage(blockBreakEvent.getPlayer(), Utils.getMessage("block.complete").replace("%salary%", block.getMoneyFormat()).replace("%block%", block.getPrefix()));
                Money.addSalary(blockBreakEvent.getPlayer(), block.getMoney());
                MineManager.addBlock(blockBreakEvent.getBlock().getLocation(), block);
                MinePlayer.add(blockBreakEvent.getPlayer(), block.getBlock().name().toLowerCase(), 1);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent blockPlaceEvent) {
        if (!blockPlaceEvent.getPlayer().hasPermission("umine.placeblocks") && Utils.getConfig().getBoolean("block-place.enable") && Mines.getMine(blockPlaceEvent.getBlock().getLocation())) {
            Utils.sendMessage(blockPlaceEvent.getPlayer(), Utils.getConfig().getString("block-place.message"));
            blockPlaceEvent.setCancelled(true);
        }

    }
}
