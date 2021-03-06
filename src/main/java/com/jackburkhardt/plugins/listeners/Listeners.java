package com.jackburkhardt.plugins.listeners;

import com.jackburkhardt.plugins.NUSMP;
import com.jackburkhardt.plugins.WorldScalars;
import io.papermc.paper.event.player.PlayerTradeEvent;
import net.ess3.api.events.PrivateMessageSentEvent;
import net.ess3.api.events.teleport.TeleportWarmupEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Listeners implements Listener {

//    @EventHandler
//    public static void onPlayerCauseCreatureSpawn(CreatureSpawnEvent event) {
//        Location l = event.getLocation();
//        World w = l.getWorld();
//        if (w.getEnvironment() == World.Environment.NORMAL && event.getEntity() instanceof Monster
//        && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
//            Random rnd = new Random();
//            int i = rnd.nextInt(1000) + 1;
//            if (i <= NUSMP.EVOKER_CHANCE) {
//                event.setCancelled(true);
//                Evoker evoker = (Evoker)w.spawnEntity(l, EntityType.EVOKER);
//                evoker.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(NUSMP.EVOKER_NEWHEALTH);
//                evoker.setHealth(NUSMP.EVOKER_NEWHEALTH);
//              //  Bukkit.broadcastMessage(ChatColor.YELLOW + "Mob EVOKER spawn at " + l + " with HEALTH " + evoker.getHealth());
//            } else if (i <= NUSMP.ILLUSONER_CHANCE) {
//                event.setCancelled(true);
//                Illusioner illusioner = (Illusioner)w.spawnEntity(l, EntityType.ILLUSIONER);
//                illusioner.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(NUSMP.ILLUSIONER_NEWHEALTH);
//                illusioner.setHealth(NUSMP.ILLUSIONER_NEWHEALTH);
//               // Bukkit.broadcastMessage(ChatColor.YELLOW + "Mob ILLUSIONER spawn at " + l + "with HEALTH " + illusioner.getHealth());
//            } else if (i <= NUSMP.VINDICATOR_CHANCE) {
//                event.setCancelled(true);
//                w.spawnEntity(l, EntityType.VINDICATOR);
//              //  Bukkit.broadcastMessage("Mob VINDICATOR spawn at " + l);
//            } else if (i <= NUSMP.CAVESPIDER_CHANCE) {
//                event.setCancelled(true);
//                w.spawnEntity(l, EntityType.CAVE_SPIDER);
//              //  Bukkit.broadcastMessage("Mob CAVESPIDER spawn at " + l);
//            }
////            try {
////                WorldScalars.mobSpawnDistanceCheck(event);
////            } catch (Exception ex) {
////               return;
////            }
////        }
//        }
//    }

    @EventHandler
    public static void onLootGenerate(LootGenerateEvent event) {
        if (event.getEntity() instanceof Player && ((Player) event.getEntity()).hasPotionEffect(PotionEffectType.LUCK)) {
            Player p = (Player)event.getEntity();
            Random rnd = new Random();
            boolean activated = false;
            for (ItemStack stack : event.getLoot()) {
                int r = rnd.nextInt(100) + 1;
                if (r <= NUSMP.LUCK_ADD_CHANCE) {
                    stack.add(1);
                    activated = true;
                    //p.sendMessage("ADDED one to stack " + stack);
                }
            }
            if (activated) {
                Location loc = p.getLocation();
                Location cloc = event.getLootContext().getLocation();
                for (float i = (float)0.5; i <= 1; i += 0.3) {
                    p.playSound(loc, Sound.BLOCK_NOTE_BLOCK_FLUTE, 5, i);
                }
                cloc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, cloc, 15,0.5,0.5,0.5);
                p.sendMessage(ChatColor.GREEN + "Your Luck potion let you find some extra items!");
            }
        }
    }

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
                Player p = event.getPlayer();
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 6, 1);
                p.sendMessage(ChatColor.YELLOW + "It looks like you're trading with a Cartographer villager. Please note that due" +
                        " to our world generation system, Cartographer ocean and mansion maps may not work properly. These structures do still generate, but they may not be located in" +
                        " the place specified by the map.");
            }
        }
    }

    @EventHandler
    public static void onEssMessagePlayer(PrivateMessageSentEvent event) {
        String name = event.getRecipient().getName();
        Player p = Bukkit.getPlayer(name);

        if (p != null) {
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 4, 1);
        }
    }

    @EventHandler
    public static void onAsyncPlayerLogin(PlayerLoginEvent event) {

        if (NUSMP.getInstance().lockedMode && !event.getPlayer().hasPermission("nusmp.lockedbypass")) {

            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Component.text(ChatColor.RED + "This server is currently not allowing player logins. See #minecraft in the Discord for updates."));
        }

    }

 /*   @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (!p.hasPlayedBefore()) {
            NUSMP.getInstance().giveStarterBook(p);
        }
    }*/
}
