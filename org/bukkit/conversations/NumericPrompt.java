/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.math.NumberUtils
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.conversations;

import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NumericPrompt
extends ValidatingPrompt {
    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return NumberUtils.isNumber((String)input) && this.isNumberValid(context, NumberUtils.createNumber((String)input));
    }

    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        return true;
    }

    @Override
    @Nullable
    protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        try {
            return this.acceptValidatedInput(context, NumberUtils.createNumber((String)input));
        }
        catch (NumberFormatException e) {
            return this.acceptValidatedInput(context, NumberUtils.INTEGER_ZERO);
        }
    }

    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext var1, @NotNull Number var2);

    @Override
    @Nullable
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        if (NumberUtils.isNumber((String)invalidInput)) {
            return this.getFailedValidationText(context, NumberUtils.createNumber((String)invalidInput));
        }
        return this.getInputNotNumericText(context, invalidInput);
    }

    @Nullable
    protected String getInputNotNumericText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return null;
    }

    @Nullable
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull Number invalidInput) {
        return null;
    }
}

