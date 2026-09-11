/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.help;

import com.google.common.base.Joiner;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.help.HelpTopic;
import org.jetbrains.annotations.NotNull;

public class GenericCommandHelpTopic
extends HelpTopic {
    protected Command command;

    public GenericCommandHelpTopic(@NotNull Command command) {
        this.command = command;
        this.name = command.getLabel().startsWith("/") ? command.getLabel() : "/" + command.getLabel();
        int i = command.getDescription().indexOf(10);
        this.shortText = i > 1 ? command.getDescription().substring(0, i - 1) : command.getDescription();
        StringBuilder sb = new StringBuilder();
        sb.append((Object)ChatColor.GOLD);
        sb.append("Description: ");
        sb.append((Object)ChatColor.WHITE);
        sb.append(command.getDescription());
        sb.append("\n");
        sb.append((Object)ChatColor.GOLD);
        sb.append("Usage: ");
        sb.append((Object)ChatColor.WHITE);
        sb.append(command.getUsage().replace("<command>", this.name.substring(1)));
        if (command.getAliases().size() > 0) {
            sb.append("\n");
            sb.append((Object)ChatColor.GOLD);
            sb.append("Aliases: ");
            sb.append((Object)ChatColor.WHITE);
            sb.append(Joiner.on((String)", ").join(command.getAliases()));
        }
        this.fullText = sb.toString();
    }

    @Override
    public boolean canSee(@NotNull CommandSender sender) {
        if (!this.command.isRegistered()) {
            return false;
        }
        if (sender instanceof ConsoleCommandSender) {
            return true;
        }
        if (this.amendedPermission != null) {
            return sender.hasPermission(this.amendedPermission);
        }
        return this.command.testPermissionSilent(sender);
    }
}

