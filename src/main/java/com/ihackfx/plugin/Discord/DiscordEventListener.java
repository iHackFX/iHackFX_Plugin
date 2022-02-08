package com.ihackfx.plugin.Discord;

import com.ihackfx.plugin.AdmChat.AdmChat;
import com.ihackfx.plugin.Plugin;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DiscordEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        Plugin plugin = Plugin.getPlugin(Plugin.class);
        if (msg.getContentRaw().startsWith("!say ") && msg.getChannel().getId().equals(plugin.getConfig().getString("DISCORD_ALL_CHAT_ID")))
        {
            Bukkit.getOnlinePlayers();
            for(Player p: Bukkit.getOnlinePlayers()){
                Bukkit.getLogger().info(p.getName());
                p.sendMessage(
                        ChatColor.BLUE + "<" + msg.getMember().getUser().getName() + ">"
                                + ChatColor.WHITE + msg.getContentRaw().substring(4)
                );
            }
        }else if(msg.getContentRaw().startsWith("!say ") && msg.getChannel().getId().equals(plugin.getConfig().getString("DISCORD_ADM_CHAT_ID"))){
            AdmChat.SayToAdmChat(msg.getContentRaw().substring(4), ChatColor.BLUE + msg.getMember().getUser().getName());
        }
    }
}