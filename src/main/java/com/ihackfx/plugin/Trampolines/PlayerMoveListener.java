package com.ihackfx.plugin.Trampolines;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlock().equals(event.getTo().getBlock())) {
            return;
        }
        Player p = event.getPlayer();
        Location l = p.getLocation();
        double y = 0;
        if (event.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.OAK_PRESSURE_PLATE) {
            y = 0;
            Material downBlock = event.getTo().getBlock().getRelative(BlockFace.DOWN).getType();
            if (downBlock == Material.IRON_BLOCK) {
                y = 1.7;
            }
            if (downBlock == Material.GOLD_BLOCK) {
                y = 2.7;
            }
            if (downBlock == Material.DIAMOND_BLOCK) {
                y = 3.5;
            }
            if (downBlock == Material.EMERALD_BLOCK) {
                y = 5;
            }
//            if (downBlock == Material.BONE_BLOCK){
//                p.teleport(new Location(Bukkit.getWorld("MiniGame"), -176, 51, -51));
//            }
            p.setVelocity(new Vector(0, y, 0));
        }
    }
}
