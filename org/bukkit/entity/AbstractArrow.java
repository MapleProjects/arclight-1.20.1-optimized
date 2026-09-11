/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AbstractArrow
extends Projectile {
    public int getKnockbackStrength();

    public void setKnockbackStrength(int var1);

    public double getDamage();

    public void setDamage(double var1);

    public int getPierceLevel();

    public void setPierceLevel(int var1);

    public boolean isCritical();

    public void setCritical(boolean var1);

    public boolean isInBlock();

    @Nullable
    public Block getAttachedBlock();

    @NotNull
    public PickupStatus getPickupStatus();

    public void setPickupStatus(@NotNull PickupStatus var1);

    public boolean isShotFromCrossbow();

    public void setShotFromCrossbow(boolean var1);

    public static enum PickupStatus {
        DISALLOWED,
        ALLOWED,
        CREATIVE_ONLY;

    }
}

