package com.georgerowland.staffmode.Listeners;

import com.georgerowland.staffmode.Commands.StaffModeCommand;
import com.georgerowland.staffmode.StaffMode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {

    // If player changes world using the TP command put them back into GAMEMODE.Spectator

    public StaffMode plugin;
    public PlayerChangeWorld(StaffMode plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onChangeWorld (PlayerChangedWorldEvent event) {
        final Player p = event.getPlayer();

        if (!StaffModeCommand.modeToggled.containsKey(p.getUniqueId())) {
            return;
        }
        else {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    p.setGameMode(GameMode.SPECTATOR);

                }
            }, 20L); //1  second delay





        }



        }




}
