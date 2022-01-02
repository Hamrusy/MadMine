package ru.hamrusy.madmine.utils;

import org.bukkit.Bukkit;
import ru.hamrusy.madmine.Main;

public class Logger {
    public Logger() {
    }

    public static void info(String s) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&b[" + Main.getInstance().getName() + "/INFO] " + s));
    }

    public static void warn(String s) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&e[" + Main.getInstance().getName() + "/WARN] " + s));
    }

    public static void error(String s) {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&c[" + Main.getInstance().getName() + "/ERROR] " + s));
    }
}
