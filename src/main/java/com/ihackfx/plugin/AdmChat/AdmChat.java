package com.ihackfx.plugin.AdmChat;

import com.ihackfx.plugin.Plugin;
import net.dv8tion.jda.api.EmbedBuilder;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.awt.*;
import java.util.Collection;


public class AdmChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] params) {
        if (sender instanceof Player) {
            if (params.length != 0) {
                SayToAdmChat(" " + String.join(" ", params), sender.getName());
                Plugin plugin = Plugin.getPlugin(Plugin.class);
                if (Plugin.BOT != null) {
                    String ChatID = plugin.getConfig().getString("DISCORD_ALL_CHAT_ID");
                    if (ChatID.length() > 5) {
                        try {
                            EmbedBuilder em = new EmbedBuilder();
                            em.setAuthor(((Player) sender).getDisplayName(), null, "https://mc-heads.net/avatar/" + sender.getName());
                            em.setColor(Color.RED);
                            em.setDescription(String.join(" ", params));
                            Plugin.BOT.getTextChannelById(plugin.getConfig().getString("DISCORD_ADM_CHAT_ID"))
                                    .sendMessageEmbeds(em.build()).queue();
                        } catch (Exception ex) {
                            plugin.getLogger().info(Plugin.NAME + " " + ex.toString());
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void SayToAdmChat(String message, String sender) {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();
            Collection<? extends Player> OnlinePlayers = Bukkit.getOnlinePlayers();
            for (Player p : OnlinePlayers) {
                User player = api.getPlayerAdapter(Player.class).getUser(p);
                Collection<Group> inheritedGroups = player.getInheritedGroups(player.getQueryOptions());
                boolean a = inheritedGroups.stream().anyMatch(g -> g.getName().equals("admin"));
                if (a) {
                    p.sendMessage(ChatColor.RED + "[ADMIN-CHAT] <" + sender + ChatColor.RED + ">:" + ChatColor.WHITE + message);
                }
            }
        }
    }
}
