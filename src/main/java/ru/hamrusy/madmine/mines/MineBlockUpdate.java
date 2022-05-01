package ru.hamrusy.madmine.mines;

import org.bukkit.Bukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import ru.hamrusy.madmine.Main;
import ru.hamrusy.madmine.utils.Utils;

public class MineBlockUpdate extends BukkitRunnable implements Listener {
    private MineBlock block;
    private Location location;
    private int time;

    public MineBlockUpdate(MineBlock block, Location location) {
        this.block = block;
        this.location = location;
        this.time = block.getUpdate();
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Location getLocation() {
        return this.location;
    }

    public void run() {
        --this.time;
        if (this.time <= 0) {
            this.reset();
        } else {
            this.setBlock(this.location);
        }

    }

    public void reset() {
        this.location.getBlock().setType(this.block.getBlock());
        this.cancel();
    }

    public void setBlock(Location location) {
        Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
            location.getBlock().setType(Material.getMaterial(Utils.getConfig().getString("setblock").toUpperCase()));
        });
    }
}
