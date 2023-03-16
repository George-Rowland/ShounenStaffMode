package com.georgerowland.staffmode.Listeners;

import com.georgerowland.staffmode.Commands.StaffModeCommand;
import com.georgerowland.staffmode.Commands.StaffModeFreezeCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player p = event.getPlayer();

        if (!StaffModeFreezeCommand.frozenPlayer.containsKey(p.getUniqueId())) {
            return;
        } else if (StaffModeFreezeCommand.frozenPlayer.containsKey(p.getUniqueId())) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&' ,"&cShounenSM: YOU HAVE BEEN FROZEN!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&' ,"&cShounenSM: Do not log out or you will be permanently banned!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&' ,"&cShounenSM: Please await a message from the staff who froze you regarding the next steps!"));
            final Location originalLocation = StaffModeFreezeCommand.frozenPlayerLocation.get(p);
            p.teleport(originalLocation);



        }
    }
}
