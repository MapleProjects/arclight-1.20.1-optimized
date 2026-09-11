/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PluginNameConversationPrefix
implements ConversationPrefix {
    protected String separator;
    protected ChatColor prefixColor;
    protected Plugin plugin;
    private String cachedPrefix;

    public PluginNameConversationPrefix(@NotNull Plugin plugin) {
        this(plugin, " > ", ChatColor.LIGHT_PURPLE);
    }

    public PluginNameConversationPrefix(@NotNull Plugin plugin, @NotNull String separator, @NotNull ChatColor prefixColor) {
        this.separator = separator;
        this.prefixColor = prefixColor;
        this.plugin = plugin;
        this.cachedPrefix = (Object)((Object)prefixColor) + plugin.getDescription().getName() + separator + (Object)((Object)ChatColor.WHITE);
    }

    @Override
    @NotNull
    public String getPrefix(@NotNull ConversationContext context) {
        return this.cachedPrefix;
    }
}

