package com.ihackfx.plugin.Discord;

import com.ihackfx.plugin.Plugin;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class SendMessagesFromChat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Plugin plugin = Plugin.getPlugin(Plugin.class);
        if(Plugin.BOT != null){
            String ChatID = plugin.getConfig().getString("DISCORD_ALL_CHAT_ID");
            if(ChatID.length() > 5){
                try {
                    EmbedBuilder em = new EmbedBuilder();
                    em.setAuthor(e.getPlayer().getDisplayName(), null, "https://mc-heads.net/avatar/" + e.getPlayer().getName());
                    em.setColor(Color.BLUE);
                    em.setDescription(e.getMessage());
                    Plugin.BOT.getTextChannelById(plugin.getConfig().getString("DISCORD_ALL_CHAT_ID"))
                            .sendMessageEmbeds(em.build()).queue();
                }catch (Exception ex){
                    plugin.getLogger().info(Plugin.NAME + " " + ex.toString());
                }
            }
        }
    }

}
