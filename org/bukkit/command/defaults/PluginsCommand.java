/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.command.defaults;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PluginsCommand
extends BukkitCommand {
    public PluginsCommand(@NotNull String name) {
        super(name);
        this.description = "Gets a list of plugins running on the server";
        this.usageMessage = "/plugins";
        this.setPermission("bukkit.command.plugins");
        this.setAliases(Arrays.asList("pl"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        sender.sendMessage("Plugins " + this.getPluginList());
        return true;
    }

    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return Collections.emptyList();
    }

    @NotNull
    private String getPluginList() {
        Plugin[] plugins;
        StringBuilder pluginList = new StringBuilder();
        Plugin[] pluginArray = plugins = Bukkit.getPluginManager().getPlugins();
        int n = plugins.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (pluginList.length() > 0) {
                pluginList.append((Object)ChatColor.WHITE);
                pluginList.append(", ");
            }
            pluginList.append((Object)(plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED));
            pluginList.append(plugin.getDescription().getName());
            if (plugin.getDescription().getProvides().size() > 0) {
                pluginList.append(" (").append(String.join((CharSequence)", ", plugin.getDescription().getProvides())).append(")");
            }
            ++n2;
        }
        return "(" + plugins.length + "): " + pluginList.toString();
    }
}

