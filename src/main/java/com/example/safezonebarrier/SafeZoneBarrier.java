package com.example.safezonebarrier;

import com.example.safezonebarrier.commands.SafeZoneCommand;
import com.example.safezonebarrier.hooks.CombatLogXHook;
import com.example.safezonebarrier.hooks.WorldGuardHook;
import com.example.safezonebarrier.listeners.PlayerMoveListener;
import com.example.safezonebarrier.listeners.PlayerTeleportListener;
import com.example.safezonebarrier.managers.BarrierManager;
import com.example.safezonebarrier.managers.ConfigManager;
import com.example.safezonebarrier.managers.CooldownManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SafeZoneBarrier extends JavaPlugin {

    private ConfigManager configManager;
    private CooldownManager cooldownManager;
    private BarrierManager barrierManager;
    private CombatLogXHook combatLogXHook;
    private WorldGuardHook worldGuardHook;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.cooldownManager = new CooldownManager(this);
        this.barrierManager = new BarrierManager(this);
        this.combatLogXHook = new CombatLogXHook();
        this.worldGuardHook = new WorldGuardHook();

        getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleportListener(this), this);
        getServer().getPluginManager().registerEvents(new com.example.safezonebarrier.listeners.EntityDamageListener(this), this);

        var cmd = getCommand("safezonebarrier");
        if (cmd != null) {
            SafeZoneCommand commandExecutor = new SafeZoneCommand(this);
            cmd.setExecutor(commandExecutor);
            cmd.setTabCompleter(commandExecutor);
        }
    }

    @Override
    public void onDisable() {
        if (barrierManager != null) {
            barrierManager.clearAll();
        }
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public BarrierManager getBarrierManager() {
        return barrierManager;
    }

    public CombatLogXHook getCombatLogXHook() {
        return combatLogXHook;
    }

    public WorldGuardHook getWorldGuardHook() {
        return worldGuardHook;
    }
}
