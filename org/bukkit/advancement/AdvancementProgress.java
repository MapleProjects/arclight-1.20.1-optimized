/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.advancement;

import java.util.Collection;
import java.util.Date;
import org.bukkit.advancement.Advancement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AdvancementProgress {
    @NotNull
    public Advancement getAdvancement();

    public boolean isDone();

    public boolean awardCriteria(@NotNull String var1);

    public boolean revokeCriteria(@NotNull String var1);

    @Nullable
    public Date getDateAwarded(@NotNull String var1);

    @NotNull
    public Collection<String> getRemainingCriteria();

    @NotNull
    public Collection<String> getAwardedCriteria();
}

