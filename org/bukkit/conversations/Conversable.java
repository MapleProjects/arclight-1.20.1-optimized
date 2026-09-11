/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import java.util.UUID;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Conversable {
    public boolean isConversing();

    public void acceptConversationInput(@NotNull String var1);

    public boolean beginConversation(@NotNull Conversation var1);

    public void abandonConversation(@NotNull Conversation var1);

    public void abandonConversation(@NotNull Conversation var1, @NotNull ConversationAbandonedEvent var2);

    public void sendRawMessage(@NotNull String var1);

    public void sendRawMessage(@Nullable UUID var1, @NotNull String var2);
}

