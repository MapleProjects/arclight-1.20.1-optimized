/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 */
package org.spigotmc;

import net.minecraft.server.MinecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TicksPerSecondCommand
extends Command {
    public TicksPerSecondCommand(String name) {
        super(name);
        this.description = "Gets the current ticks per second for the server";
        this.usageMessage = "/tps";
        this.setPermission("bukkit.command.tps");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        StringBuilder sb = new StringBuilder((Object)((Object)ChatColor.GOLD) + "TPS from last 1m, 5m, 15m: ");
        double[] dArray = MinecraftServer.getServer().recentTps;
        int n = MinecraftServer.getServer().recentTps.length;
        int n2 = 0;
        while (n2 < n) {
            double tps = dArray[n2];
            sb.append(this.format(tps));
            sb.append(", ");
            ++n2;
        }
        sender.sendMessage(sb.substring(0, sb.length() - 2));
        sender.sendMessage((Object)((Object)ChatColor.GOLD) + "Current Memory Usage: " + (Object)((Object)ChatColor.GREEN) + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 0x100000L + "/" + Runtime.getRuntime().totalMemory() / 0x100000L + " mb (Max: " + Runtime.getRuntime().maxMemory() / 0x100000L + " mb)");
        return true;
    }

    private String format(double tps) {
        return String.valueOf((tps > 18.0 ? ChatColor.GREEN : (tps > 16.0 ? ChatColor.YELLOW : ChatColor.RED)).toString()) + (tps > 20.0 ? "*" : "") + Math.min((double)Math.round(tps * 100.0) / 100.0, 20.0);
    }
}

