/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.jetbrains.annotations.NotNull;

public interface ConversationCanceller
extends Cloneable {
    public void setConversation(@NotNull Conversation var1);

    public boolean cancelBasedOnInput(@NotNull ConversationContext var1, @NotNull String var2);

    @NotNull
    public ConversationCanceller clone();
}

