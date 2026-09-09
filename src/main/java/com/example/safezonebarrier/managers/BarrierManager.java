package com.example.safezonebarrier.managers;

import com.example.safezonebarrier.SafeZoneBarrier;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BarrierManager {

    private final SafeZoneBarrier plugin;
    private final Map<UUID, Set<Location>> activePlayerBlocks = new HashMap<>();
    private final BukkitTask cleanupTask;

    public BarrierManager(SafeZoneBarrier plugin) {
        this.plugin = plugin;
        this.cleanupTask = Bukkit.getScheduler().runTaskTimer(plugin, this::cleanupInactiveCombat, 20L, 20L);
    }

    public void updateProximityBarrier(Player player) {
        if (!plugin.getCombatLogXHook().isInCombat(player)) {
            clearBarrier(player);
            return;
        }

        World world = player.getWorld();
        Location center = player.getLocation();
        int cx = center.getBlockX();
        int cy = center.getBlockY();
        int cz = center.getBlockZ();
        int radius = plugin.getConfigManager().getBarrierRadius();
        int heightBelow = plugin.getConfigManager().getBarrierHeightBelow();
        int heightAbove = plugin.getConfigManager().getBarrierHeightAbove();

        Set<Location> newBlocks = new HashSet<>();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                if ((x - cx) * (x - cx) + (z - cz) * (z - cz) > radius * radius) {
                    continue;
                }
                for (int y = cy - heightBelow; y <= cy + heightAbove; y++) {
                    Location loc = new Location(world, x, y, z);
                    if (plugin.getWorldGuardHook().isPvpDenied(loc) && loc.getBlock().getType().isAir()) {
                        newBlocks.add(loc);
                    }
                }
            }
        }

        UUID uuid = player.getUniqueId();
        Set<Location> current = activePlayerBlocks.getOrDefault(uuid, Collections.emptySet());

        BlockData airData = Bukkit.createBlockData(Material.AIR);
        BlockData glassData = Bukkit.createBlockData(plugin.getConfigManager().getBarrierMaterial());

        for (Location loc : current) {
            if (!newBlocks.contains(loc)) {
                player.sendBlockChange(loc, airData);
            }
        }

        for (Location loc : newBlocks) {
            if (!current.contains(loc)) {
                player.sendBlockChange(loc, glassData);
            }
        }

        if (newBlocks.isEmpty()) {
            activePlayerBlocks.remove(uuid);
        } else {
            activePlayerBlocks.put(uuid, newBlocks);
        }
    }

    public void clearBarrier(Player player) {
        Set<Location> current = activePlayerBlocks.remove(player.getUniqueId());
        if (current != null && !current.isEmpty() && player.isOnline()) {
            BlockData airData = Bukkit.createBlockData(Material.AIR);
            for (Location loc : current) {
                player.sendBlockChange(loc, airData);
            }
        }
    }

    public void clearAll() {
        if (cleanupTask != null) {
            cleanupTask.cancel();
        }
        BlockData airData = Bukkit.createBlockData(Material.AIR);
        for (Map.Entry<UUID, Set<Location>> entry : activePlayerBlocks.entrySet()) {
            Player player = Bukkit.getPlayer(entry.getKey());
            if (player != null && player.isOnline()) {
                for (Location loc : entry.getValue()) {
                    player.sendBlockChange(loc, airData);
                }
            }
        }
        activePlayerBlocks.clear();
    }

    private void cleanupInactiveCombat() {
        Set<UUID> toRemove = new HashSet<>();
        for (UUID uuid : activePlayerBlocks.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null || !player.isOnline() || !plugin.getCombatLogXHook().isInCombat(player)) {
                toRemove.add(uuid);
            }
        }
        for (UUID uuid : toRemove) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                clearBarrier(player);
            } else {
                activePlayerBlocks.remove(uuid);
            }
        }
    }
}
