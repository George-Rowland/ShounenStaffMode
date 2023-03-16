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
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class StaffModeFreezeCommand implements CommandExecutor {


    Plugin plugin;

    // Hashmap for whether ability is toggled
    public static HashMap<UUID, Boolean> frozenPlayer;
    public static HashMap<Player, Location> frozenPlayerLocation;

    public StaffModeFreezeCommand() {
        this.plugin = StaffMode.getPlugin((Class) StaffMode.class);
        this.frozenPlayer = new HashMap<>();
        this.frozenPlayerLocation = new HashMap<>();
    }

    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&cShounenSM: This command can only be used by a player!"));
        }
        else {
        Player p = (Player) sender;
        if (!p.hasPermission("shounenstaffmode.staffmodefreeze")) {
            String NoPermission = StaffMode.getInstance().config.getString("Commands.StaffModeFreeze.NoPermission");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&' , NoPermission));
            return true;
        }
        else if (!StaffModeCommand.modeToggled.containsKey(p.getUniqueId())){
            p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&cShounenSM: This command can only be used in /staffmode!"));
            return true;
        }
        else {
            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);

                if (!this.frozenPlayer.containsKey(target.getUniqueId())) {
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "-----------------------------------------"));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: YOU HAVE BEEN FROZEN BY " + sender.getName()));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: Do not log out or you will be permanently banned!"));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: Please await a message from the staff who froze you regarding the next steps!"));
                    target.setInvulnerable(true);
                    target.setGameMode(GameMode.ADVENTURE);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: &aYou have frozen " + args[0]));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: Please contact " + args[0] + " via /msg right away!"));
                    this.frozenPlayer.put(target.getUniqueId(), true);
                    this.frozenPlayerLocation.put(target, target.getLocation());
                } else if (this.frozenPlayer.containsKey(target.getUniqueId())) {
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&cShounenSM: &aYou have been unfrozen!"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: &aYou have unfrozen " + args[0]));
                    StaffModeFreezeCommand.frozenPlayerLocation.remove(target);
                    StaffModeFreezeCommand.frozenPlayer.remove(target.getUniqueId());
                    target.setInvulnerable(false);
                    target.setGameMode(GameMode.SURVIVAL);

                }

                return true;
                }
                else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: The player " + args[0] + " is not online!"));
                return true;
                }
            }
            else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&cShounenSM: &cCorrent usage /freeze [IGN]"));
                return true;
            }

        }

        }





        return false;
    }
}
