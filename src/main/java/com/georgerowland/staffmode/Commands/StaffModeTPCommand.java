package com.georgerowland.staffmode.Commands;

import com.georgerowland.staffmode.StaffMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffModeTPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "ShounenStaffMode: This command can only be used by a player!"));

        } else {
            Player p = (Player) sender;
            if (!p.hasPermission("shounenstaffmode.staffmodetp")) {
                String NoPermission = StaffMode.getInstance().config.getString("Commands.StaffModeTP.NoPermission");
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
                p.teleport(target.getLocation());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&cShounenSM: &aYou have been teleported to " + args[0]));
                return true;
                }
                else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cShounenSM: The player " + args[0] + " is not online!"));
                return true;
                }
                }
                else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&cShounenSM: &cCorrent usage /tp [IGN]"));
                return true;
                }


            }


        }

        return false;
    }
}
