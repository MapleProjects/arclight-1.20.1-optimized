/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 */
package org.bukkit.craftbukkit.v1_20_R1.help;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;

public class CommandAliasHelpTopic
extends HelpTopic {
    private final String aliasFor;
    private final HelpMap helpMap;

    public CommandAliasHelpTopic(String alias, String aliasFor, HelpMap helpMap) {
        this.aliasFor = aliasFor.startsWith("/") ? aliasFor : "/" + aliasFor;
        this.helpMap = helpMap;
        this.name = alias.startsWith("/") ? alias : "/" + alias;
        Preconditions.checkArgument((!this.name.equals(this.aliasFor) ? 1 : 0) != 0, (String)"Command %s cannot be alias for itself", (Object)this.name);
        this.shortText = (Object)((Object)ChatColor.YELLOW) + "Alias for " + (Object)((Object)ChatColor.WHITE) + this.aliasFor;
    }

    @Override
    public String getFullText(CommandSender forWho) {
        Preconditions.checkArgument((forWho != null ? 1 : 0) != 0, (Object)"CommandServer forWho cannot be null");
        StringBuilder sb = new StringBuilder(this.shortText);
        HelpTopic aliasForTopic = this.helpMap.getHelpTopic(this.aliasFor);
        if (aliasForTopic != null) {
            sb.append("\n");
            sb.append(aliasForTopic.getFullText(forWho));
        }
        return sb.toString();
    }

    @Override
    public boolean canSee(CommandSender commandSender) {
        Preconditions.checkArgument((commandSender != null ? 1 : 0) != 0, (Object)"CommandServer cannot be null");
        if (this.amendedPermission == null) {
            HelpTopic aliasForTopic = this.helpMap.getHelpTopic(this.aliasFor);
            if (aliasForTopic != null) {
                return aliasForTopic.canSee(commandSender);
            }
            return false;
        }
        return commandSender.hasPermission(this.amendedPermission);
    }
}

