package com.jackburkhardt.plugins;

import com.jackburkhardt.plugins.listeners.Listeners;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public final class NUSMP extends JavaPlugin {

    private static NUSMP instance;
    public static WorldScalers scalarInstance = new WorldScalers();

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new Listeners(), this);
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("noreload")) {
            sender.sendMessage(ChatColor.RED + "/reload has been disabled, as it causes CPU and memory leaks. Please reload" +
                    " individual plugins or restart the server.");
            return true;
        }
// meow
//        if (command.getName().equalsIgnoreCase("meow")) {
//            meower.getWorld().playSound(meower.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
//            return true;
//        }

    return true;
    }

    public static NUSMP getInstance() {

        return instance;

    }
    public static WorldScalers getScalarInstance() {
        return scalarInstance;
    }
}
