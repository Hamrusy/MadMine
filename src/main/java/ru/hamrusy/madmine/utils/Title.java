package ru.hamrusy.madmine.utils;

import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Title {
    public Title() {
    }

    public static void sendTitle(Player player, String s) {
        sendTitle(player, s, 15, 60, 15);
    }

    public static void sendTitle(Player player, String translateAlternateColorCodes, int n, int n2, int n3) {
        translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', translateAlternateColorCodes);
        String[] array = translateAlternateColorCodes.split("%nl%");

        try {
            String s = array[0];
            sendPacket(player, ((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("TIMES").get((Object)null), ((Class)Objects.requireNonNull(getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\": \"" + s + "\"}"), n, n2, n3));
            sendPacket(player, ((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent")).newInstance(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("TITLE").get((Object)null), ((Class)Objects.requireNonNull(getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\": \"" + s + "\"}")));
            if (array.length == 2) {
                String s2 = array[1];
                sendPacket(player, ((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("TIMES").get((Object)null), ((Class)Objects.requireNonNull(getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\": \"" + s2 + "\"}"), n, n2, n3));
                sendPacket(player, ((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(((Class)Objects.requireNonNull(getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("SUBTITLE").get((Object)null), ((Class)Objects.requireNonNull(getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\": \"" + s2 + "\"}"), n, n2, n3));
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    private static void sendPacket(Player player, Object o) {
        try {
            Object invoke = player.getClass().getMethod("getHandle", (Class[])(new Class[0])).invoke(player);
            Object value = invoke.getClass().getField("playerConnection").get(invoke);
            value.getClass().getMethod("sendPacket", getNMS("Packet")).invoke(value, o);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    private static Class<?> getNMS(String s) {
        String s2 = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            return Class.forName("net.minecraft.server." + s2 + "." + s);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
