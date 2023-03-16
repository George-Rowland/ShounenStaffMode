package com.georgerowland.staffmode;

import com.georgerowland.staffmode.Commands.StaffModeCommand;
import com.georgerowland.staffmode.Commands.StaffModeFreezeCommand;
import com.georgerowland.staffmode.Commands.StaffModeTPCommand;
import com.georgerowland.staffmode.Listeners.PlayerMove;
import com.georgerowland.staffmode.Listeners.PlayerQuit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffMode extends JavaPlugin {

    public static StaffMode instance = null;
    public FileConfiguration config;

    public static StaffMode getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        StaffMode.instance = this;
        config = this.getConfig();
        config.options().copyDefaults(true);
        this.saveConfig();

        // Register Commands
        getCommand("staffmode").setExecutor(new StaffModeCommand());
        getCommand("tp").setExecutor(new StaffModeTPCommand());
        getCommand("freeze").setExecutor(new StaffModeFreezeCommand());

        // Register Listeners
        this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMove(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
