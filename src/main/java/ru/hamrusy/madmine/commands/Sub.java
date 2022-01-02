package ru.hamrusy.madmine.commands;

import org.bukkit.entity.Player;
import ru.hamrusy.madmine.utils.Utils;

public abstract class Sub extends Utils {
    public Sub() {
    }

    public abstract boolean execute(Player var1, String[] var2);

    public abstract String command();

    public abstract String permission();

    public abstract String description();

    public abstract String[] aliases();
}
