package com.jackburkhardt.plugins.listeners;

import com.jackburkhardt.plugins.NUSMP;
import com.jackburkhardt.plugins.WorldScalars;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listeners implements Listener {

//    // TODO: Put location listener on like a 30s timer or something
//    @EventHandler
//    public static void onPlayerMove(PlayerMoveEvent event) {
//        //NUSMP.climate.checkPlayerClimate(event.getPlayer());
//    }

    @EventHandler
    public static void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Monster) {
            try {
                WorldScalars.mobSpawnDistanceCheck(event);
            } catch (Exception ex) {
               return;
            }
        }
    }

    @EventHandler
    public static void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
            LivingEntity e = (LivingEntity)event.getEntity();
            event.getDamager().sendMessage("Mob now on health " + e.getHealth());
        }
    }
}
