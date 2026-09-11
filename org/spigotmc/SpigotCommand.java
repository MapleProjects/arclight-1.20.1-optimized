/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 */
package org.spigotmc;

import java.io.File;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.spigotmc.SpigotConfig;

public class SpigotCommand
extends Command {
    public SpigotCommand(String name) {
        super(name);
        this.description = "Spigot related commands";
        this.usageMessage = "/spigot reload";
        this.setPermission("bukkit.command.spigot");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!this.testPermission(sender)) {
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage((Object)((Object)ChatColor.RED) + "Usage: " + this.usageMessage);
            return false;
        }
        if (args[0].equals("reload")) {
            Command.broadcastCommandMessage(sender, (Object)((Object)ChatColor.RED) + "Please note that this command is not supported and may cause issues.");
            Command.broadcastCommandMessage(sender, (Object)((Object)ChatColor.RED) + "If you encounter any issues please use the /stop command to restart your server.");
            MinecraftServer console = MinecraftServer.getServer();
            SpigotConfig.init((File)console.options.valueOf("spigot-settings"));
            for (ServerLevel world : console.m_129785_()) {
                world.spigotConfig.init();
            }
            ++console.server.reloadCount;
            Command.broadcastCommandMessage(sender, (Object)((Object)ChatColor.GREEN) + "Reload complete.");
        }
        return true;
    }
}

