package com.jackburkhardt.plugins.listeners;

import com.jackburkhardt.plugins.NUSMP;
import com.jackburkhardt.plugins.WorldScalers;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listeners implements Listener {

    // TODO: Put location listener on like a 30s timer or something
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        //NUSMP.climate.checkPlayerClimate(event.getPlayer());
    }

    @EventHandler
    public static void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Monster) {
            WorldScalers.mobModifier(event);
        }
    }
}
