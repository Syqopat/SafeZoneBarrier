package com.example.safezonebarrier.managers;

import com.example.safezonebarrier.SafeZoneBarrier;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ConfigManager {

    private final SafeZoneBarrier plugin;

    public ConfigManager(SafeZoneBarrier plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
    }

    public String getMessage(String path) {
        String message = plugin.getConfig().getString(path);
        if (message == null || message.isEmpty()) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public Material getBarrierMaterial() {
        String name = plugin.getConfig().getString("barrier.material", "RED_STAINED_GLASS");
        Material mat = Material.matchMaterial(name);
        return mat != null ? mat : Material.RED_STAINED_GLASS;
    }

    public int getBarrierRadius() {
        return Math.max(1, plugin.getConfig().getInt("barrier.radius", 4));
    }

    public int getBarrierHeightBelow() {
        return Math.max(0, plugin.getConfig().getInt("barrier.height-below", 1));
    }

    public int getBarrierHeightAbove() {
        return Math.max(1, plugin.getConfig().getInt("barrier.height-above", 3));
    }

    public boolean isBlockEntryEnabled() {
        return plugin.getConfig().getBoolean("features.block-entry", true);
    }

    public boolean isBlockTeleportEnabled() {
        return plugin.getConfig().getBoolean("features.block-teleport", true);
    }

    public boolean isElytraDamageProtectionEnabled() {
        return plugin.getConfig().getBoolean("features.elytra-kinetic-damage-protection", true);
    }

    public long getMessageCooldownMillis() {
        return plugin.getConfig().getLong("settings.message-cooldown-seconds", 3) * 1000L;
    }

    public void reloadConfig() {
        plugin.reloadConfig();
    }
}
