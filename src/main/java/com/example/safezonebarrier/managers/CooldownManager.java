package com.example.safezonebarrier.managers;

import com.example.safezonebarrier.SafeZoneBarrier;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final SafeZoneBarrier plugin;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public CooldownManager(SafeZoneBarrier plugin) {
        this.plugin = plugin;
    }

    public boolean canSendMessage(Player player) {
        long now = System.currentTimeMillis();
        UUID uuid = player.getUniqueId();
        long cooldown = plugin.getConfigManager().getMessageCooldownMillis();
        if (!cooldowns.containsKey(uuid) || now - cooldowns.get(uuid) >= cooldown) {
            cooldowns.put(uuid, now);
            return true;
        }
        return false;
    }
}
