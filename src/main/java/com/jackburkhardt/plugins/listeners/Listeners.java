package com.jackburkhardt.plugins.listeners;

import com.jackburkhardt.plugins.NUSMP;
import com.jackburkhardt.plugins.WorldScalars;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import io.papermc.paper.event.player.PlayerTradeEvent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Listeners implements Listener {

    @EventHandler
    public static void onPlayerCauseCreatureSpawn(CreatureSpawnEvent event) {
        Location l = event.getLocation();
        World w = l.getWorld();
        if (w.getEnvironment() == World.Environment.NORMAL && event.getEntity() instanceof Monster
        && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            Random rnd = new Random();
            int i = rnd.nextInt(1000) + 1;
            if (i <= 2) {
                event.setCancelled(true);
                w.spawnEntity(l, EntityType.EVOKER);
                Bukkit.broadcastMessage("Mob EVOKER spawn at " + l);
            } else if (i <= 6) {
                event.setCancelled(true);
                w.spawnEntity(l, EntityType.ILLUSIONER);
                Bukkit.broadcastMessage("Mob ILLUSIONER spawn at " + l);
            } else if (i <= 20) {
                event.setCancelled(true);
                w.spawnEntity(l, EntityType.VINDICATOR);
                Bukkit.broadcastMessage("Mob VINDICATOR spawn at " + l);
            } else if (i <= 40) {
                event.setCancelled(true);
                w.spawnEntity(l, EntityType.CAVE_SPIDER);
                Bukkit.broadcastMessage("Mob CAVESPIDER spawn at " + l);
            }
//            try {
//                WorldScalars.mobSpawnDistanceCheck(event);
//            } catch (Exception ex) {
//               return;
//            }
//        }
        }
    }
//
//    @EventHandler
//    public static void onEntityDamageEntity(EntityDamageByEntityEvent event) {
//        if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
//            LivingEntity e = (LivingEntity)event.getEntity();
//            event.getDamager().sendMessage("Mob now on health " + e.getHealth());
//        }
//    }
    @EventHandler
    public static void onPlayerTrade(PlayerTradeEvent event) {
        if (event.getVillager().getType() != EntityType.WANDERING_TRADER) {
            if (((Villager) event.getVillager()).getProfession() == Villager.Profession.CARTOGRAPHER && event.getTrade().getIngredients().contains(new ItemStack(Material.COMPASS))) {
                event.getPlayer().sendMessage(ChatColor.YELLOW + "It looks like you're trading with a Cartographer villager. Please note that due" +
                        " to our world generation system, Cartographer ocean and mansion maps will not work properly!");
            }
        }
    }
}
