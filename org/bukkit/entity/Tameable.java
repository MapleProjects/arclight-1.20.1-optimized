/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Animals;
import org.jetbrains.annotations.Nullable;

public interface Tameable
extends Animals {
    public boolean isTamed();

    public void setTamed(boolean var1);

    @Nullable
    public AnimalTamer getOwner();

    public void setOwner(@Nullable AnimalTamer var1);
}

