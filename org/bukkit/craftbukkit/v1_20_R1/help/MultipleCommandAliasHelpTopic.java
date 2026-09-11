/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.help;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.MultipleCommandAlias;
import org.bukkit.help.HelpTopic;

public class MultipleCommandAliasHelpTopic
extends HelpTopic {
    private final MultipleCommandAlias alias;

    public MultipleCommandAliasHelpTopic(MultipleCommandAlias alias) {
        this.alias = alias;
        this.name = "/" + alias.getLabel();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < alias.getCommands().length) {
            if (i != 0) {
                sb.append((Object)((Object)ChatColor.GOLD) + " > " + (Object)((Object)ChatColor.WHITE));
            }
            sb.append("/");
            sb.append(alias.getCommands()[i].getLabel());
            ++i;
        }
        this.shortText = sb.toString();
        this.fullText = (Object)((Object)ChatColor.GOLD) + "Alias for: " + (Object)((Object)ChatColor.WHITE) + this.getShortText();
    }

    @Override
    public boolean canSee(CommandSender sender) {
        if (this.amendedPermission == null) {
            if (sender instanceof ConsoleCommandSender) {
                return true;
            }
            Command[] commandArray = this.alias.getCommands();
            int n = commandArray.length;
            int n2 = 0;
            while (n2 < n) {
                Command command = commandArray[n2];
                if (!command.testPermissionSilent(sender)) {
                    return false;
                }
                ++n2;
            }
            return true;
        }
        return sender.hasPermission(this.amendedPermission);
    }
}

