/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;

public abstract class StringPrompt
implements Prompt {
    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return true;
    }
}

