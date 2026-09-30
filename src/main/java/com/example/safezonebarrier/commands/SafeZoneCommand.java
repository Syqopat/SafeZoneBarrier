package com.example.safezonebarrier.commands;

import com.example.safezonebarrier.SafeZoneBarrier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SafeZoneCommand implements CommandExecutor, TabCompleter {

    private final SafeZoneBarrier plugin;

    public SafeZoneCommand(SafeZoneBarrier plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("safezonebarrier.reload")) {
            plugin.getConfigManager().reloadConfig();
            plugin.getBarrierManager().clearAll();
            String msg = plugin.getConfigManager().getMessage("messages.reload");
            sender.sendMessage(msg.isEmpty() ? "Config reloaded." : msg);
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1 && sender.hasPermission("safezonebarrier.reload")) {
            if ("reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }
        }
        return completions;
    }
}
