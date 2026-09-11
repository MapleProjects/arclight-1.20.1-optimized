/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import org.bukkit.conversations.ConversationContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Prompt
extends Cloneable {
    public static final Prompt END_OF_CONVERSATION = null;

    @NotNull
    public String getPromptText(@NotNull ConversationContext var1);

    public boolean blocksForInput(@NotNull ConversationContext var1);

    @Nullable
    public Prompt acceptInput(@NotNull ConversationContext var1, @Nullable String var2);
}

