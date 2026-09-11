/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import java.util.EventListener;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.jetbrains.annotations.NotNull;

public interface ConversationAbandonedListener
extends EventListener {
    public void conversationAbandoned(@NotNull ConversationAbandonedEvent var1);
}

