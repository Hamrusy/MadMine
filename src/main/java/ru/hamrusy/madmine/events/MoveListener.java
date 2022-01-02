package ru.hamrusy.madmine.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.hamrusy.madmine.utils.Checks;
import ru.hamrusy.madmine.mines.Mines;

public class MoveListener implements Listener {
    public MoveListener() {
    }

    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent) {
        if (Mines.getMine(playerMoveEvent.getPlayer().getLocation())) {
            Checks.check(playerMoveEvent.getPlayer());
        }

    }
}
