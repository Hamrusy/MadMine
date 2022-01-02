package ru.hamrusy.madmine;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.hamrusy.madmine.commands.CommandManager;
import ru.hamrusy.madmine.events.BlockListener;
import ru.hamrusy.madmine.events.MoveListener;
import ru.hamrusy.madmine.mines.MineManager;
import ru.hamrusy.madmine.mines.MinePlayer;
import ru.hamrusy.madmine.mines.Mines;
import ru.hamrusy.madmine.utils.Logger;
import ru.hamrusy.madmine.utils.Money;

public class Main extends JavaPlugin {
    private static Main instance;

    public Main() {
    }

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        Mines.loadBlocks();
        this.getCommand("mine").setExecutor(new CommandManager());
        this.getServer().getPluginManager().registerEvents(new BlockListener(), this);
        this.getServer().getPluginManager().registerEvents(new MoveListener(), this);
        PlaceholderAPI.registerPlaceholderHook("mmine", new MinePlayer());
        new Money();

        Logger.info("Плагин успешно включён.");
    }

    public void onDisable() {
        MineManager.reset();
    }

    public static WorldEditPlugin getWorldEdit() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldEdit");
        return plugin != null && plugin instanceof WorldEditPlugin ? (WorldEditPlugin)plugin : null;
    }
}