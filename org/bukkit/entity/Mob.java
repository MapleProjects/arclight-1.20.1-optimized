/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.Nullable;

public interface Mob
extends LivingEntity,
Lootable {
    public void setTarget(@Nullable LivingEntity var1);

    @Nullable
    public LivingEntity getTarget();

    public void setAware(boolean var1);

    public boolean isAware();

    @Nullable
    public Sound getAmbientSound();
}

