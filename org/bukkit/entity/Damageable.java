/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface Damageable
extends Entity {
    public void damage(double var1);

    public void damage(double var1, @Nullable Entity var3);

    public double getHealth();

    public void setHealth(double var1);

    public double getAbsorptionAmount();

    public void setAbsorptionAmount(double var1);

    @Deprecated
    public double getMaxHealth();

    @Deprecated
    public void setMaxHealth(double var1);

    @Deprecated
    public void resetMaxHealth();
}

