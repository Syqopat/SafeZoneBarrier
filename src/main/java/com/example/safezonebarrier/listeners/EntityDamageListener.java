package com.example.safezonebarrier.listeners;

import com.example.safezonebarrier.SafeZoneBarrier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    private final SafeZoneBarrier plugin;

    public EntityDamageListener(SafeZoneBarrier plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
            return;
        }

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (plugin.getConfigManager().isElytraDamageProtectionEnabled() && plugin.getCombatLogXHook().isInCombat(player)) {
            event.setCancelled(true);
        }
    }
}
