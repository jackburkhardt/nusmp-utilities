package com.jackburkhardt.plugins;

import com.jackburkhardt.plugins.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;



public final class NUSMP extends JavaPlugin {

    private static NUSMP instance;
    //public static WorldScalars scalarInstance = new WorldScalars();
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

        if (command.getName().equalsIgnoreCase("givestarterbook")) {
            if (args.length == 0) {
                giveStarterBook((Player)sender);
                sender.sendMessage("You've been given a copy of the starter book.");
                return true;
            } else if (args.length == 1 && sender.hasPermission("nusmp.givebookother")) {
                Player p;
                try {
                    p = Bukkit.getPlayer(args[0]);
                    giveStarterBook(p);
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "That player is either not online or does not exist.");
                }
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("nusmp")) {
            if (args.length == 0) {
                sender.sendMessage("nusmp-utilities is a plugin made by Jack Burkhardt for the Northwestern Survival Server. " +
                        "Current version: " + getDescription().getVersion() + ", based on Paper API version " + getDescription().getAPIVersion());
            } else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("nusmp.reload")) {
                reloadConfig();
                reloadConfVars();
                sender.sendMessage(ChatColor.GREEN + "NUSMP plugin config reloaded.");
            }
        }

    return true;
    }

    public void giveStarterBook(Player p) {
        String command = "give " + p.getName() + " written_book{pages:['[\"\",{\"text\":\"Welcome to the NU Minecraft Server!\",\"bold\":true,\"underlined\":true,\"color\":\"#4E2A84\"},{\"text\":\"\\\\n\\\\nThis book includes some optional extra info and tips to help you take advantage of what\\'s available around here!\\\\n\\\\nFeel free to flip through to learn more. \",\"color\":\"reset\"},{\"text\":\"Green text\",\"bold\":true,\"color\":\"green\"},{\"text\":\" indicates clickable links!\",\"color\":\"reset\"}]','[\"\",{\"text\":\"The Map\",\"bold\":true,\"underlined\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\\\\nWe have an online, live-updating map of the world. You can check out what other people are up to or see your builds from a different perspective!\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"[View the map]\",\"bold\":true,\"color\":\"green\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://map.numc.me\"}}]','[\"\",{\"text\":\"McMMO\",\"bold\":true,\"underlined\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\\\\nWe have a plugin called McMMO that adds optional skills and abilities you can level up, such as farming, combat, etc. You don\\'t have to bother with these, but they exist if you\\'re interested!\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"[McMMO Wiki]\",\"bold\":true,\"color\":\"green\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://mcmmo.org/wiki/Main_Page\"}},{\"text\":\"\\\\n\",\"color\":\"reset\",\"bold\":true},{\"text\":\"[/mcmmo help]\",\"bold\":true,\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/mcmmo help\"}}]','[\"\",{\"text\":\"1.18 Caves\",\"bold\":true,\"underlined\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\\\\nWe are using a Mojang datapack that adds the new 1.18 style caves. This means that our world is now a lot taller and caving is a more involved experience. You\\'ll also notice new cave biomes. such as Lush Caves!\",\"color\":\"reset\"}]','[\"\",{\"text\":\"New Biomes\",\"bold\":true,\"underlined\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\\\\nWe have some new biomes. They\\'re pretty cool. That\\'s about it. The goal was to keep them as vanilla-like as possible while still feeling fresh.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"New Structures\",\"bold\":true,\"underlined\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\\\\nYou may encounter several new randomly generated structures. Beware; some may have traps, hidden loot, or hard mobs.\\\\n\\\\nYou can also find Luck potions in some structures that gives you a chance to find extra loot in chests!\\\\n \",\"color\":\"reset\"}]','[\"\",{\"text\":\"Extra commands\",\"bold\":true,\"underlined\":true,\"color\":\"gold\"},{\"text\":\"\\\\n\\\\nThere are a few extra commands to make your life a bit easier. You can view many of them by doing /help, or just click the link below for a list of the important ones.\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"[Commands List]\",\"bold\":true,\"color\":\"green\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://docs.google.com/document/d/1M3Pj1upfYACaIvH_9hX_CI2bOQamLUoKqvhOX_qquZ0/edit\"}}]','[\"\",{\"text\":\"That\\'s it! Make sure to read /rules, and feel free to ask any admin with a \"},{\"text\":\"yellow name\",\"color\":\"yellow\"},{\"text\":\" for additonal help (or Jack on Discord).\\\\n\\\\nWe\\'ve tried to design this to add some more spice to Minecraft while still feeling and playing similar to standard vanilla Minecraft.\",\"color\":\"reset\"}]'],title:\"Welcome to the Server!\",author:\"Jack (nongs)\",display:{Lore:[\"You could read it. Or not, I guess.\"]}}";
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
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
//    public static WorldScalars getScalarInstance() {
//        return scalarInstance;
//    }
}
