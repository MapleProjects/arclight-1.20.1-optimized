/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.help;

import java.util.Collection;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.help.HelpTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IndexHelpTopic
extends HelpTopic {
    protected String permission;
    protected String preamble;
    protected Collection<HelpTopic> allTopics;

    public IndexHelpTopic(@NotNull String name, @Nullable String shortText, @Nullable String permission, @NotNull Collection<HelpTopic> topics) {
        this(name, shortText, permission, topics, null);
    }

    public IndexHelpTopic(@NotNull String name, @Nullable String shortText, @Nullable String permission, @NotNull Collection<HelpTopic> topics, @Nullable String preamble) {
        this.name = name;
        this.shortText = shortText == null ? "" : shortText;
        this.permission = permission;
        this.preamble = preamble == null ? "" : preamble;
        this.setTopicsCollection(topics);
    }

    protected void setTopicsCollection(@NotNull Collection<HelpTopic> topics) {
        this.allTopics = topics;
    }

    @Override
    public boolean canSee(@NotNull CommandSender sender) {
        if (sender instanceof ConsoleCommandSender) {
            return true;
        }
        if (this.permission == null) {
            return true;
        }
        return sender.hasPermission(this.permission);
    }

    @Override
    public void amendCanSee(@Nullable String amendedPermission) {
        this.permission = amendedPermission;
    }

    @Override
    @NotNull
    public String getFullText(@NotNull CommandSender sender) {
        StringBuilder sb = new StringBuilder();
        if (this.preamble != null) {
            sb.append(this.buildPreamble(sender));
            sb.append("\n");
        }
        for (HelpTopic topic : this.allTopics) {
            if (!topic.canSee(sender)) continue;
            String lineStr = this.buildIndexLine(sender, topic).replace("\n", ". ");
            if (sender instanceof Player && lineStr.length() > 55) {
                sb.append(lineStr, 0, 52);
                sb.append("...");
            } else {
                sb.append(lineStr);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @NotNull
    protected String buildPreamble(@NotNull CommandSender sender) {
        return (Object)((Object)ChatColor.GRAY) + this.preamble;
    }

    @NotNull
    protected String buildIndexLine(@NotNull CommandSender sender, @NotNull HelpTopic topic) {
        StringBuilder line = new StringBuilder();
        line.append((Object)ChatColor.GOLD);
        line.append(topic.getName());
        line.append(": ");
        line.append((Object)ChatColor.WHITE);
        line.append(topic.getShortText());
        return line.toString();
    }
}

