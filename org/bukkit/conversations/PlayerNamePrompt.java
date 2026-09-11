/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PlayerNamePrompt
extends ValidatingPrompt {
    private Plugin plugin;

    public PlayerNamePrompt(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return this.plugin.getServer().getPlayer(input) != null;
    }

    @Override
    @Nullable
    protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        return this.acceptValidatedInput(context, this.plugin.getServer().getPlayer(input));
    }

    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext var1, @NotNull Player var2);
}

