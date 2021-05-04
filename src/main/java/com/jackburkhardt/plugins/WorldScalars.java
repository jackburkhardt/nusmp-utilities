package com.jackburkhardt.plugins;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class WorldScalars {

    public static void mobSpawnDistanceCheck(CreatureSpawnEvent event) {
        Location spawnLoc = event.getLocation();
        LivingEntity entity = event.getEntity();
        for (Player p : spawnLoc.getNearbyPlayers(100)) {
                // THIS ENTIRE SYSTEM COULD PROBABLY BE USING AttributeModifiers. it will not for now.
                double newHealth = createHealthModifiers(entity, p);
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newHealth);
                entity.setHealth(newHealth);
                entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(createDamageModifiers(entity, p));
                break;
        }
    }
    // These two modifier functions are seperated so the math can be isolated and tweaked easily.
    public static double createHealthModifiers(LivingEntity e, Player p) {

        double stdHealth = e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        double newHealth;

        newHealth = stdHealth + (ExperienceAPI.getLevel(p, PrimarySkillType.SWORDS) + ExperienceAPI.getLevel(p, PrimarySkillType.AXES)) / 20;

        //debug
        p.sendMessage("Spawned mob with health " + newHealth);
        return newHealth;
    }
    public static double createDamageModifiers(LivingEntity e, Player p) {
        double stdDamage = e.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
        double newDamage;

        newDamage = stdDamage + (ExperienceAPI.getLevel(p, PrimarySkillType.SWORDS) + ExperienceAPI.getLevel(p, PrimarySkillType.AXES)) / 35;

        //debug
        p.sendMessage("Spawned mob with damage " + newDamage);
        return newDamage;
    }
}
