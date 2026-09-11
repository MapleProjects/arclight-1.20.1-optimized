/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationCanceller;
import org.bukkit.conversations.ConversationContext;
import org.jetbrains.annotations.NotNull;

public class ExactMatchConversationCanceller
implements ConversationCanceller {
    private String escapeSequence;

    public ExactMatchConversationCanceller(@NotNull String escapeSequence) {
        this.escapeSequence = escapeSequence;
    }

    @Override
    public void setConversation(@NotNull Conversation conversation) {
    }

    @Override
    public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input) {
        return input.equals(this.escapeSequence);
    }

    @Override
    @NotNull
    public ConversationCanceller clone() {
        return new ExactMatchConversationCanceller(this.escapeSequence);
    }
}

