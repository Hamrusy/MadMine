package ru.hamrusy.madmine;

import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionBar {
    public ActionBar() {
    }

    public static void sendActionBar(Player player, String translateAlternateColorCodes) {
        translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', translateAlternateColorCodes);

        try {
            sendPacket(player, ((Class)Objects.requireNonNull(getNMS("PacketPlayOutChat"))).getConstructor(getNMS("IChatBaseComponent"), getNMS("ChatMessageType")).newInstance(((Class)Objects.requireNonNull(getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\": \"" + translateAlternateColorCodes + "\"}"), ((Class)Objects.requireNonNull(getNMS("ChatMessageType"))).getField("GAME_INFO").get((Object)null)));
        } catch (Exception var3) {
            var3.printStackTrace();
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
