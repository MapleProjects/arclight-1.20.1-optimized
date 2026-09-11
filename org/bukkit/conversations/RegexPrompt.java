/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.conversations;

import java.util.regex.Pattern;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ValidatingPrompt;
import org.jetbrains.annotations.NotNull;

public abstract class RegexPrompt
extends ValidatingPrompt {
    private Pattern pattern;

    public RegexPrompt(@NotNull String regex) {
        this(Pattern.compile(regex));
    }

    public RegexPrompt(@NotNull Pattern pattern) {
        this.pattern = pattern;
    }

    private RegexPrompt() {
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return this.pattern.matcher(input).matches();
    }
}

