package com.jackburkhardt.plugins;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.player.PlayerProfile;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class WorldScalers {

    public static void mobModifier(CreatureSpawnEvent event) {
        Location spawnLoc = event.getLocation();
        LivingEntity entity = event.getEntity();
        for (Player p : NUSMP.getInstance().getServer().getOnlinePlayers()) {
            if (spawnLoc.distanceSquared(p.getLocation()) <= 625) {
                double newHealth = entity.getHealth() + ExperienceAPI.getPowerLevel(p) / 10;
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newHealth);
                p.sendMessage("Spawned mob with health " + newHealth);
            }
        }
    }
}
