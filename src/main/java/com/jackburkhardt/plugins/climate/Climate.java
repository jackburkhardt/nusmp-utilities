package com.jackburkhardt.plugins.climate;

import net.kyori.adventure.text.ComponentBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public class Climate {

    public void checkPlayerBiome(Player player) {
        Biome pb = player.getLocation().getBlock().getBiome();
        for (ClimateBiomes cb : ClimateBiomes) {
            Biome currentCBiome = cb.getBiome();
            Temperature currentCBTemp = cb.getTemp();
            if (currentCBiome == pb) {
                if (currentCBTemp == Temperature.HOT) {
                    player.sendMessage(ChatColor.GOLD + "You're starting to feel warm...\n" + ChatColor.GRAY +
                            ChatColor.ITALIC + "Consider taking off some armor or putting on something lighter. (Hover)");
                } if (currentCBTemp == Temperature.COLD) {
                    player.sendMessage(ChatColor.AQUA + "You're starting to feel chilly...\n" + ChatColor.GRAY +
                            ChatColor.ITALIC + "Consider putting on more armor, especially something thicker. (Hover)");
                }

            }
            break;
        }
    }

}
