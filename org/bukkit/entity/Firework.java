/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Firework
extends Projectile {
    @NotNull
    public FireworkMeta getFireworkMeta();

    public void setFireworkMeta(@NotNull FireworkMeta var1);

    public boolean setAttachedTo(@Nullable LivingEntity var1);

    @Nullable
    public LivingEntity getAttachedTo();

    public boolean setLife(int var1);

    public int getLife();

    public boolean setMaxLife(int var1);

    public int getMaxLife();

    public void detonate();

    public boolean isDetonated();

    public boolean isShotAtAngle();

    public void setShotAtAngle(boolean var1);
}

