package ru.hamrusy.madmine.mines;

import com.sk89q.worldedit.bukkit.selections.Selection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import ru.hamrusy.madmine.Config;
import ru.hamrusy.madmine.Utils;

public class Mines {
    private static FileConfiguration config;
    private static List<MineBlock> blocks = new ArrayList();

    public Mines() {
    }

    public static FileConfiguration getConfig() {
        return config != null ? config : (config = Config.getFile("mines.yml"));
    }

    public static void saveConfig() {
        Config.save(getConfig(), "mines.yml");
    }

    public static void removeMine(String s) {
        getConfig().set(s, (Object)null);
        saveConfig();
    }

    public static void saveMine(String s, Selection selection) {
        getConfig().set(s + ".min.x", selection.getMinimumPoint().getX());
        getConfig().set(s + ".min.y", selection.getMinimumPoint().getY());
        getConfig().set(s + ".min.z", selection.getMinimumPoint().getZ());
        getConfig().set(s + ".max.x", selection.getMaximumPoint().getX());
        getConfig().set(s + ".max.y", selection.getMaximumPoint().getY());
        getConfig().set(s + ".max.z", selection.getMaximumPoint().getZ());
        getConfig().set(s + ".world", selection.getWorld().getName());
        saveConfig();
    }

    public static void loadBlocks() {
        Iterator var0 = Utils.getConfig().getConfigurationSection("ores").getKeys(false).iterator();

        while(var0.hasNext()) {
            String s = (String)var0.next();
            MineBlock mineBlock = new MineBlock();
            mineBlock.setBlock(Material.getMaterial(s.toUpperCase()));
            mineBlock.setMoney(Double.parseDouble(Utils.getConfig().getString("ores." + s + ".money")));
            mineBlock.setUpdate(Utils.getConfig().getInt("ores." + s + ".update"));
            mineBlock.setPrefix(Utils.getConfig().getString("ores." + s + ".prefix") != null ? Utils.getConfig().getString("ores." + s + ".prefix") : mineBlock.getBlock().name());
            blocks.add(mineBlock);
        }

    }

    public static MineBlock getBlock(Material material) {
        Iterator var1 = blocks.iterator();

        MineBlock mineBlock;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            mineBlock = (MineBlock)var1.next();
        } while(mineBlock.getBlock() != material);

        return mineBlock;
    }

    public static MineInfo getMine(String name) {
        if (getConfig().getString(name) == null) {
            return null;
        } else {
            MineInfo mineInfo = new MineInfo();
            World world = Bukkit.getWorld(getConfig().getString(name + ".world"));
            mineInfo.setName(name);
            mineInfo.setMax(new Location(world, getConfig().getDouble(name + ".max.x"), getConfig().getDouble(name + ".max.y"), getConfig().getDouble(name + ".max.z")));
            mineInfo.setMin(new Location(world, getConfig().getDouble(name + ".min.x"), getConfig().getDouble(name + ".min.y"), getConfig().getDouble(name + ".min.z")));
            mineInfo.setWorld(world);
            return mineInfo;
        }
    }

    public static boolean getMine(Location location) {
        Iterator iterator = getConfig().getConfigurationSection("").getKeys(false).iterator();

        MineInfo mine;
        double x;
        double y;
        double z;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            mine = getMine((String)iterator.next());
            x = location.getX();
            y = location.getY();
            z = location.getZ();
        } while(!(Math.min(mine.getMin().getX(), mine.getMax().getX()) <= x) || !(Math.min(mine.getMin().getY(), mine.getMax().getY()) <= y) || !(Math.min(mine.getMin().getZ(), mine.getMax().getZ()) <= z) || !(Math.max(mine.getMin().getX(), mine.getMax().getX()) >= x) || !(Math.max(mine.getMin().getY(), mine.getMax().getY()) >= y) || !(Math.max(mine.getMin().getZ(), mine.getMax().getZ()) >= z));

        return true;
    }

    public static List<MineInfo> getMines() {
        ArrayList<MineInfo> list = new ArrayList();
        Iterator iterator = getConfig().getConfigurationSection("").getKeys(false).iterator();

        while(iterator.hasNext()) {
            list.add(getMine((String)iterator.next()));
        }

        return list;
    }
}
