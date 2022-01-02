package ru.hamrusy.madmine.mines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import ru.hamrusy.madmine.Main;

public class MineManager {
    private static HashMap<Location, MineBlockUpdate> blocks = new HashMap();

    public MineManager() {
    }

    public static void addBlock(Location location, MineBlock mineBlock) {
        if (getBlock(location) == null || getBlock(location).isCancelled()) {
            blocks.put(location, new MineBlockUpdate(mineBlock, location));
        }

        ((MineBlockUpdate) blocks.get(location)).runTaskTimer(Main.getInstance(), 0L, 20L);
    }

    public static MineBlockUpdate getBlock(Location location) {
        return (MineBlockUpdate) blocks.get(location);
    }

    public static void reset() {
        blocks.values().forEach(MineManager::lambda$reset$0);
    }

    public static List<MineBlockUpdate> getBlocks() {
        return new ArrayList(blocks.values());
    }

    private static void lambda$reset$0(MineBlockUpdate mineBlockUpdate) {
        mineBlockUpdate.reset();
    }
}
