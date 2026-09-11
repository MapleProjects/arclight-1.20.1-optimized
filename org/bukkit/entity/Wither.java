/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Boss;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Wither
extends Monster,
Boss {
    @Override
    public void setTarget(@Nullable LivingEntity var1);

    public void setTarget(@NotNull Head var1, @Nullable LivingEntity var2);

    @Nullable
    public LivingEntity getTarget(@NotNull Head var1);

    public int getInvulnerabilityTicks();

    public void setInvulnerabilityTicks(int var1);

    public static enum Head {
        CENTER,
        LEFT,
        RIGHT;

    }
}

