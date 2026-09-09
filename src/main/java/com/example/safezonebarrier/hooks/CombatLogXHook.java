package com.example.safezonebarrier.hooks;

import com.github.sirblobman.combatlogx.api.ICombatLogX;
import com.github.sirblobman.combatlogx.api.manager.ICombatManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CombatLogXHook {

    public boolean isInCombat(Player player) {
        if (!Bukkit.getPluginManager().isPluginEnabled("CombatLogX")) {
            return false;
        }
        ICombatLogX plugin = (ICombatLogX) Bukkit.getPluginManager().getPlugin("CombatLogX");
        if (plugin != null) {
            ICombatManager combatManager = plugin.getCombatManager();
            return combatManager.isInCombat(player);
        }
        return false;
    }
}
