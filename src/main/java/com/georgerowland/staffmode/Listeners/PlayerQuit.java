package com.georgerowland.staffmode.Listeners;

import com.georgerowland.staffmode.Commands.StaffModeCommand;
import com.georgerowland.staffmode.StaffMode;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player p = event.getPlayer();

        if (!StaffModeCommand.modeToggled.containsKey(p.getUniqueId())) {
            return; }

        else if (StaffModeCommand.modeToggled.containsKey(p.getUniqueId())){
            String ToggledOff = StaffMode.getInstance().config.getString("Commands.StaffMode.ToggledOff");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&' , ToggledOff));
            p.setGameMode(GameMode.SURVIVAL);
            p.setInvisible(false);
            final Location originalLocation = StaffModeCommand.playerLocation.get(p);
            p.teleport(originalLocation);
            StaffModeCommand.modeToggled.remove(p.getUniqueId());




            }
        }


    }

