/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Set;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BooleanPrompt
extends ValidatingPrompt {
    private static final Set<String> TRUE_INPUTS = ImmutableSet.of((Object)"true", (Object)"on", (Object)"yes", (Object)"y", (Object)"1", (Object)"right", (Object[])new String[]{"correct", "valid"});
    private static final Set<String> FALSE_INPUTS = ImmutableSet.of((Object)"false", (Object)"off", (Object)"no", (Object)"n", (Object)"0", (Object)"wrong", (Object[])new String[]{"incorrect", "invalid"});
    private static final Set<String> VALID_INPUTS = ImmutableSet.builder().addAll(TRUE_INPUTS).addAll(FALSE_INPUTS).build();

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return VALID_INPUTS.contains(input.toLowerCase(Locale.ROOT));
    }

    @Override
    @Nullable
    protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        return this.acceptValidatedInput(context, TRUE_INPUTS.contains(input.toLowerCase(Locale.ROOT)));
    }

    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext var1, boolean var2);
}

