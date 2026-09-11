/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.List;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ValidatingPrompt;
import org.jetbrains.annotations.NotNull;

public abstract class FixedSetPrompt
extends ValidatingPrompt {
    protected List<String> fixedSet;

    public FixedSetPrompt(String ... fixedSet) {
        this.fixedSet = Arrays.asList(fixedSet);
    }

    private FixedSetPrompt() {
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return this.fixedSet.contains(input);
    }

    @NotNull
    protected String formatFixedSet() {
        return "[" + Joiner.on((String)", ").join(this.fixedSet) + "]";
    }
}

