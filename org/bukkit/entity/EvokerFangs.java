/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public interface EvokerFangs
extends Entity {
    @Nullable
    public LivingEntity getOwner();

    public void setOwner(@Nullable LivingEntity var1);
}

