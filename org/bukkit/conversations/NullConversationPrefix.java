/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;
import org.jetbrains.annotations.NotNull;

public class NullConversationPrefix
implements ConversationPrefix {
    @Override
    @NotNull
    public String getPrefix(@NotNull ConversationContext context) {
        return "";
    }
}

