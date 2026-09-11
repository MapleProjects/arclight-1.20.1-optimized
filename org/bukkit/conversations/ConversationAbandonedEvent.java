/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import java.util.EventObject;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationCanceller;
import org.bukkit.conversations.ConversationContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConversationAbandonedEvent
extends EventObject {
    private ConversationContext context;
    private ConversationCanceller canceller;

    public ConversationAbandonedEvent(@NotNull Conversation conversation) {
        this(conversation, null);
    }

    public ConversationAbandonedEvent(@NotNull Conversation conversation, @Nullable ConversationCanceller canceller) {
        super(conversation);
        this.context = conversation.getContext();
        this.canceller = canceller;
    }

    @Nullable
    public ConversationCanceller getCanceller() {
        return this.canceller;
    }

    @NotNull
    public ConversationContext getContext() {
        return this.context;
    }

    public boolean gracefulExit() {
        return this.canceller == null;
    }
}

