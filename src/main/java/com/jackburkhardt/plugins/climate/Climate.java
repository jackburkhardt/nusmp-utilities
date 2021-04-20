package com.jackburkhardt.plugins.climate;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Climate {

    public ClimateBiomes[] biomes = {ClimateBiomes.DESERT, ClimateBiomes.TUNDRA};

    public void checkPlayerClimate(Player player) {
        Biome pb = player.getLocation().getBlock().getBiome();
        if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
            player.sendMessage(ChatColor.RED + "You're starting to feel really hot...\n" + ChatColor.GRAY +
                    ChatColor.ITALIC + "Consider wearing lighter armor. (Hover)");
        }
        for (ClimateBiomes cb : biomes) {
            Biome currentCBiome = cb.getBiome();
            Temperature currentCBTemp = cb.getTemp();
            if (currentCBiome == pb) {
                if (currentCBTemp == Temperature.HOT && !checkPlayerPrepared(player, currentCBTemp)) {

                    player.sendMessage(ChatColor.GOLD + "You're starting to feel warm...\n" + ChatColor.GRAY +
                            ChatColor.ITALIC + "Consider taking off some armor or putting on something lighter. (Hover)");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));

                } if (currentCBTemp == Temperature.COLD && !checkPlayerPrepared(player, currentCBTemp)) {

                    player.sendMessage(ChatColor.AQUA + "You're starting to feel chilly...\n" + ChatColor.GRAY +
                            ChatColor.ITALIC + "Consider putting on more armor, especially something thicker. (Hover)");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
                    // TODO: 1.17 freeze effect?

                }
                break;
            }
        }
    }

    public boolean checkPlayerPrepared(Player player, Temperature temp) {
        short filledSlots = (short)player.getInventory().getArmorContents().length;
        // TODO: global armor weight system?
        if (temp == Temperature.HOT) {
            if (filledSlots > 3) return false;
            return true;
        } if (temp == Temperature.COLD) {
            if (filledSlots < 3) return false;
            return true;
        }
        return true;
    }

}
