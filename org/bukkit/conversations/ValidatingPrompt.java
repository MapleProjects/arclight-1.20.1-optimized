/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ValidatingPrompt
implements Prompt {
    @Override
    @Nullable
    public Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        if (this.isInputValid(context, input)) {
            return this.acceptValidatedInput(context, input);
        }
        String failPrompt = this.getFailedValidationText(context, input);
        if (failPrompt != null) {
            context.getForWhom().sendRawMessage((Object)((Object)ChatColor.RED) + failPrompt);
        }
        return this;
    }

    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return true;
    }

    protected abstract boolean isInputValid(@NotNull ConversationContext var1, @NotNull String var2);

    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext var1, @NotNull String var2);

    @Nullable
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return null;
    }
}

