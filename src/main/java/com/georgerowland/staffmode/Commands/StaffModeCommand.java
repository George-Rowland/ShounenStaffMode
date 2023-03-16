package com.georgerowland.staffmode.Commands;

import com.georgerowland.staffmode.StaffMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class StaffModeCommand implements CommandExecutor {
        Plugin plugin;

        // Hashmap for whether ability is toggled
        public static HashMap<UUID, Boolean> modeToggled;


        // Hashmap for player location
        public static HashMap<Player, Location> playerLocation;

        public StaffModeCommand() {
            this.plugin = StaffMode.getPlugin((Class) StaffMode.class);
            this.playerLocation = new HashMap<>();
            this.modeToggled = new HashMap<>();
        }

        @Override
        public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {

            //If Player
            if (sender instanceof Player) {
                Player p = (Player) sender;


                // If No Permission
                if (!p.hasPermission("shounenstaffmode.staffmode")) {
                    String NoPermission = StaffMode.getInstance().config.getString("Commands.StaffMode.NoPermission");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', NoPermission));
                    return true;
                }

                // If Permission
                else if (p.hasPermission("shounenstaffmode.staffmode")) {

                    // If Disabled (not in staff mode)
                if (!this.modeToggled.containsKey(p.getUniqueId())) {

                    this.modeToggled.put(p.getUniqueId(), true);
                    String ToggledOn = StaffMode.getInstance().config.getString("Commands.StaffMode.ToggledOn");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&' , ToggledOn));
                    p.setGameMode(GameMode.SPECTATOR);
                    p.setInvisible(true);

                    //Save Location
                    this.playerLocation.put(p , p.getLocation());
                    return true;
                    }

                    // If Enabled (in staff mode)
                    else {
                    String ToggledOff = StaffMode.getInstance().config.getString("Commands.StaffMode.ToggledOff");
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&' , ToggledOff));
                    p.setGameMode(GameMode.SURVIVAL);
                    p.setInvisible(false);



                    // Load and tp to original location
                    final Location originalLocation = this.playerLocation.get(p);
                    p.teleport(originalLocation);

                    // remove from Toggled HashMap
                    modeToggled.remove(p.getUniqueId());
                    return true;
                }



                }
            }

            return false;
        }

    }

