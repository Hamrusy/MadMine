package ru.hamrusy.madmine.events;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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
            if (MineManager.getBlock(blockBreakEvent.getBlock().getLocation()) != null && blockBreakEvent.getBlock().getType() == Material.getMaterial(Utils.getConfig().getString("block").toUpperCase())) {
                blockBreakEvent.setCancelled(true);
            } else if (Mines.getBlock(blockBreakEvent.getBlock().getType()) == null) {
                if (!blockBreakEvent.getPlayer().hasPermission("mmine.breakblocks") && Utils.getConfig().getBoolean("block-break.enable")) {
                    Utils.sendMessage(blockBreakEvent.getPlayer(), Utils.getConfig().getString("block-break.message"));
                    blockBreakEvent.setCancelled(true);
                }

            } else {
                final Player player = blockBreakEvent.getPlayer();
                if (MinePlayer.getBackpack(player) == MinePlayer.getBackpackMax(player)) {
                    Utils.sendMessage(player, Utils.getMessage("block.full"));
                    blockBreakEvent.setCancelled(true);
                    return;
                }
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
        if (!blockPlaceEvent.getPlayer().hasPermission("mmine.placeblocks") && Utils.getConfig().getBoolean("block-place.enable") && Mines.getMine(blockPlaceEvent.getBlock().getLocation())) {
            Utils.sendMessage(blockPlaceEvent.getPlayer(), Utils.getConfig().getString("block-place.message"));
            blockPlaceEvent.setCancelled(true);
        }

    }
}
