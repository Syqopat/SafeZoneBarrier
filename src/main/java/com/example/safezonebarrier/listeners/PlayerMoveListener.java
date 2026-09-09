package com.example.safezonebarrier.listeners;

import com.example.safezonebarrier.SafeZoneBarrier;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerMoveListener implements Listener {

    private final SafeZoneBarrier plugin;

    public PlayerMoveListener(SafeZoneBarrier plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (to == null) {
            return;
        }

        Player player = event.getPlayer();

        if (!plugin.getCombatLogXHook().isInCombat(player)) {
            plugin.getBarrierManager().clearBarrier(player);
            return;
        }

        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
            plugin.getBarrierManager().updateProximityBarrier(player);
        }

        if (plugin.getConfigManager().isBlockEntryEnabled() && plugin.getWorldGuardHook().isPvpDenied(to)) {
            event.setCancelled(true);

            if (plugin.getCooldownManager().canSendMessage(player)) {
                String msg = plugin.getConfigManager().getMessage("messages.barrier-hit");
                if (!msg.isEmpty()) {
                    player.sendMessage(msg);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getBarrierManager().clearBarrier(event.getPlayer());
    }
}
