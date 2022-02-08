package com.ihackfx.plugin;

import com.ihackfx.plugin.AdmChat.AdmChat;
import com.ihackfx.plugin.Discord.DiscordEventListener;
import com.ihackfx.plugin.Discord.SendMessagesFromChat;
import com.ihackfx.plugin.Trampolines.PlayerMoveListener;
import com.ihackfx.plugin.TrollingClass.TrollTab;
import com.ihackfx.plugin.TrollingClass.Trolling;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class Plugin extends JavaPlugin{
    public static String NAME = ChatColor.GREEN + "["  + "iHackFX Plugin" + "]" + ChatColor.GREEN;
    public static JDA BOT = null;
    @Override
    public void onEnable() {
        if(getConfig().getString("DISCORD_API_KEY").length() < 5){
            this.saveDefaultConfig();
            this.getLogger().info(NAME + " For enable bot functions, please write token in config.yml");
        }else{
            try {
                BOT = JDABuilder.createLight(getConfig().getString("DISCORD_API_KEY"))
                        .addEventListeners(new DiscordEventListener())
                        .build();
                this.getLogger().info(NAME + " BOT is started!");
            } catch (LoginException e) {
                this.getLogger().info(NAME + " BOT is not started!");
                this.getLogger().info(NAME + ChatColor.RED + " ERROR: \n" + e.getMessage().toString());
                this.getLogger().info(NAME + ChatColor.RED + " Please check DISCORD_API_TOKEN on config.yml!");
            }
        }
        Bukkit.getLogger().info(NAME + " Loaded");
        this.getCommand("troll").setExecutor(new Trolling());
        this.getCommand("troll").setTabCompleter(new TrollTab());
        this.getCommand("a").setExecutor(new AdmChat());
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        this.getServer().getPluginManager().registerEvents(new SendMessagesFromChat(), this);
    }

    @Override
    public void onDisable(){
        BOT.shutdownNow(); // To reload permanently
        BOT.shutdownNow(); // on reload plugin, bot
        BOT.shutdownNow(); // doesn't stop properly
        BOT = null;
        Bukkit.getLogger().info(NAME + " DISABLED ");
    }

    public static void SayToConsole(String message){
        Bukkit.getLogger().info(NAME + message);
    }
}
