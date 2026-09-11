/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.help;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class HelpTopic {
    protected String name = "";
    protected String shortText = "";
    protected String fullText = "";
    protected String amendedPermission = null;

    public abstract boolean canSee(@NotNull CommandSender var1);

    public void amendCanSee(@Nullable String amendedPermission) {
        this.amendedPermission = amendedPermission;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String getShortText() {
        return this.shortText;
    }

    @NotNull
    public String getFullText(@NotNull CommandSender forWho) {
        return this.fullText;
    }

    public void amendTopic(@Nullable String amendedShortText, @Nullable String amendedFullText) {
        this.shortText = this.applyAmendment(this.shortText, amendedShortText);
        this.fullText = this.applyAmendment(this.fullText, amendedFullText);
    }

    @NotNull
    protected String applyAmendment(@NotNull String baseText, @Nullable String amendment) {
        if (amendment == null) {
            return baseText;
        }
        return amendment.replaceAll("<text>", baseText);
    }
}

