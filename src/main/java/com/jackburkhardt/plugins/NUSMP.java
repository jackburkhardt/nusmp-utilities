package com.jackburkhardt.plugins;

import com.jackburkhardt.plugins.climate.Climate;
import com.jackburkhardt.plugins.listeners.Listeners;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public final class NUSMP extends JavaPlugin {

    private static NUSMP instance;
    public static Climate climate;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new Listeners(), this);
        instance = this;
        climate = new Climate();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("noreload")) {
            sender.sendMessage(ChatColor.RED + "Imagine using /reload in 2021. Tsk tsk.");
        }

        if (command.getName().equalsIgnoreCase("meow")) {
            Player meower = sender.getServer().getPlayer(sender.getName());
            meower.getWorld().playSound(meower.getLocation(), Sound.ENTITY_CAT_PURREOW, 10, 1);
        }

        return true;
    }

    public static NUSMP getInstance() {

        return instance;

    }
}
