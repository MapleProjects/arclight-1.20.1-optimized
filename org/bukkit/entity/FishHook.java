/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FishHook
extends Projectile {
    public int getMinWaitTime();

    public void setMinWaitTime(int var1);

    public int getMaxWaitTime();

    public void setMaxWaitTime(int var1);

    public void setWaitTime(int var1, int var2);

    public int getMinLureTime();

    public void setMinLureTime(int var1);

    public int getMaxLureTime();

    public void setMaxLureTime(int var1);

    public void setLureTime(int var1, int var2);

    public float getMinLureAngle();

    public void setMinLureAngle(float var1);

    public float getMaxLureAngle();

    public void setMaxLureAngle(float var1);

    public void setLureAngle(float var1, float var2);

    public boolean getApplyLure();

    public void setApplyLure(boolean var1);

    @Deprecated
    public double getBiteChance();

    @Deprecated
    public void setBiteChance(double var1) throws IllegalArgumentException;

    public boolean isInOpenWater();

    @Nullable
    public Entity getHookedEntity();

    public void setHookedEntity(@Nullable Entity var1);

    public boolean pullHookedEntity();

    public boolean isSkyInfluenced();

    public void setSkyInfluenced(boolean var1);

    public boolean isRainInfluenced();

    public void setRainInfluenced(boolean var1);

    @NotNull
    public HookState getState();

    public static enum HookState {
        UNHOOKED,
        HOOKED_ENTITY,
        BOBBING;

    }
}

