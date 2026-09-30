package com.example.safezonebarrier.listeners;

import com.example.safezonebarrier.SafeZoneBarrier;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportListener implements Listener {

    private final SafeZoneBarrier plugin;

    public PlayerTeleportListener(SafeZoneBarrier plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Location to = event.getTo();
        Player player = event.getPlayer();

        if (to == null) {
            return;
        }

        if (plugin.getConfigManager().isBlockTeleportEnabled() && plugin.getCombatLogXHook().isInCombat(player) && plugin.getWorldGuardHook().isPvpDenied(to)) {
            event.setCancelled(true);

            if (plugin.getCooldownManager().canSendMessage(player)) {
                String msg = plugin.getConfigManager().getMessage("messages.teleport-denied");
                if (!msg.isEmpty()) {
                    player.sendMessage(msg);
                }
            }
        }
    }
}
