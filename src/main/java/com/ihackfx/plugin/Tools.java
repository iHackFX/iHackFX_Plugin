package com.ihackfx.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Tools {
    public static String NAME = ChatColor.GREEN + "["  + "iHackFX Plugin" + "]" + ChatColor.GREEN;

    public static org.bukkit.entity.Player playerIsOnline(String Player, CommandSender sender){
        org.bukkit.entity.Player PlayerObj = null;
        if (Player.length() == 0){
            sender.sendMessage(NAME + " Пожалуйста введите ник игрока");
            return null;
        }
        PlayerObj = Bukkit.getPlayerExact(Player);
        if (PlayerObj == null){
            sender.sendMessage(NAME + " Игрок " + Player + " не найден");
            return null;
        }
        return PlayerObj;
    }
}
