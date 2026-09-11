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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MessagePrompt
implements Prompt {
    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return false;
    }

    @Override
    @Nullable
    public Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        return this.getNextPrompt(context);
    }

    @Nullable
    protected abstract Prompt getNextPrompt(@NotNull ConversationContext var1);
}

