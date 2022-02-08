package com.ihackfx.plugin.TrollingClass;

import com.ihackfx.plugin.Tools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;


public class Trolling implements CommandExecutor {
    public static String NAME = ChatColor.GREEN + "["  + "iHackFX Plugin" + "]" + ChatColor.GREEN;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        switch (args[0]) {
            case "push":
                return pushPlayer(sender, command, label, args);
            case "vomit":
                return vomitPlayer(sender, command, label, args);
            case "invtroll":
                return inventoryTroll(sender, command, label, args);
        }
        return false;
    }

    public static boolean inventoryTroll(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = Tools.playerIsOnline(args[1], sender);
            if (p != null) {
                sender.sendMessage(NAME + " У Игрока " + args[1] + " исчезает и появляется инвентарь");
                ItemStack[] invItems = p.getInventory().getContents();
                ItemStack[] invArmor = p.getInventory().getArmorContents();
                p.getInventory().clear();
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
                        Bukkit.getPluginManager().getPlugin("iHackFX_Plugin"),
                        ()-> {
                            p.getInventory().setArmorContents(invArmor);
                            p.getInventory().setContents(invItems);
                        }, 60
                );
                return true;
            }
        }
        return false;
    }

    public static boolean vomitPlayer(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player blindPlayer = Tools.playerIsOnline(args[1], sender);
            if(blindPlayer != null){
                sender.sendMessage(NAME + " Игрок " + args[1] + " теперь колбасится");
                int task_ID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(
                        Bukkit.getPluginManager().getPlugin("iHackFX_Plugin"),
                        () -> {
                            blindPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15, 10));
                            blindPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 5));
                        }, 1, 5
                );
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
                        Bukkit.getPluginManager().getPlugin("iHackFX_Plugin"),
                        ()-> Bukkit.getServer().getScheduler().cancelTask(task_ID), 60
                );
                return true;
            }
        }
        return false;
    }

    public static boolean pushPlayer(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            Player pushedPlayer = Tools.playerIsOnline(args[1], sender);
            if(pushedPlayer != null) {
                sender.sendMessage(NAME + " Игрок " + args[1] + " подкинут");
                int task_ID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(
                        Bukkit.getPluginManager().getPlugin("iHackFX_Plugin"),
                        () -> pushedPlayer.setVelocity(new Vector(0, 128, 0)), 1, 20
                );
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(
                        Bukkit.getPluginManager().getPlugin("iHackFX_Plugin"),
                        ()-> Bukkit.getServer().getScheduler().cancelTask(task_ID), 60
                );
                return true;
            }
        }
        return true;
    }
}

