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
    public static WorldScalars scalarInstance = new WorldScalars();
    public static int EVOKER_CHANCE, VINDICATOR_CHANCE, CAVESPIDER_CHANCE, ILLUSONER_CHANCE;
    public static int EVOKER_NEWHEALTH, ILLUSIONER_NEWHEALTH, LUCK_ADD_CHANCE;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new Listeners(), this);
        instance = this;
        saveDefaultConfig();

        reloadConfVars();

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
        if (command.getName().equalsIgnoreCase("poggers")) {
            sender.sendMessage("⠄⠄⠄⠄⠄⠄⣀⣀⣀⣤⣶⣿⣿⣶⣶⣶⣤⣄⣠⣴⣶⣿⣿⣿⣿⣶⣦⣄⠄⠄\n" +
                    "⠄⠄⣠⣴⣾⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦\n" +
                    "⢠⠾⣋⣭⣄⡀⠄⠄⠈⠙⠻⣿⣿⡿⠛⠋⠉⠉⠉⠙⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⡎⣾⡟⢻⣿⣷⠄⠄⠄⠄⠄⡼⣡⣾⣿⣿⣦⠄⠄⠄⠄⠄⠈⠛⢿⣿⣿⣿⣿⣿\n" +
                    "⡇⢿⣷⣾⣿⠟⠄⠄⠄⠄⢰⠁⣿⣇⣸⣿⣿⠄⠄⠄⠄⠄⠄⠄⣠⣼⣿⣿⣿⣿\n" +
                    "⢸⣦⣭⣭⣄⣤⣤⣤⣴⣶⣿⣧⡘⠻⠛⠛⠁⠄⠄⠄⠄⣀⣴⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⠄⢉⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣦⣶⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⢰⡿⠛⠛⠛⠛⠻⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⠸⡇⠄⠄⢀⣀⣀⠄⠄⠄⠄⠄⠉⠉⠛⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⠄⠈⣆⠄⠄⢿⣿⣿⣿⣷⣶⣶⣤⣤⣀⣀⡀⠄⠄⠉⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⠄⠄⣿⡀⠄⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠂⠄⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⠄⠄⣿⡇⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⠄⠄⣿⡇⠄⠠⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠄⠄⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⠄⠄⣿⠁⠄⠐⠛⠛⠛⠛⠉⠉⠉⠉⠄⠄⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿\n" +
                    "⠄⠄⠻⣦⣀⣀⣀⣀⣀⣀⣤⣤⣤⣤⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠄");
            return true;
        }
// meow
        if (command.getName().equalsIgnoreCase("meow")) {
            Player meower = (Player)sender;
            meower.getWorld().playSound(meower.getLocation(), Sound.ENTITY_CAT_AMBIENT, 10, 1);
            return true;
        }

        if (command.getName().equalsIgnoreCase("nusmp")) {
            if (args.length == 0) {
                sender.sendMessage("nusmp-utilities is a plugin made by Jack Burkhardt for the Northwestern Survival Server.");
            }
            if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("nusmp.reload")) {
                reloadConfig();
                reloadConfVars();
                sender.sendMessage(ChatColor.GREEN + "NUSMP plugin config reloaded.");
            }
        }

    return true;
    }

    public void reloadConfVars() {
        EVOKER_CHANCE = getConfig().getInt("evokerChance");
        EVOKER_NEWHEALTH = getConfig().getInt("evokerHealth");
        ILLUSONER_CHANCE = getConfig().getInt("illusionerChance");
        ILLUSIONER_NEWHEALTH = getConfig().getInt("illusionerHealth");
        VINDICATOR_CHANCE = getConfig().getInt("vindicatorChance");
        CAVESPIDER_CHANCE = getConfig().getInt("caveSpiderChance");
        LUCK_ADD_CHANCE = getConfig().getInt("addOneChance");
    }

    public static NUSMP getInstance() {

        return instance;

    }
    public static WorldScalars getScalarInstance() {
        return scalarInstance;
    }
}
