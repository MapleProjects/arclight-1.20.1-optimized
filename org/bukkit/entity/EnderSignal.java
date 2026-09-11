/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EnderSignal
extends Entity {
    @NotNull
    public Location getTargetLocation();

    public void setTargetLocation(@NotNull Location var1);

    public boolean getDropItem();

    public void setDropItem(boolean var1);

    @NotNull
    public ItemStack getItem();

    public void setItem(@Nullable ItemStack var1);

    public int getDespawnTimer();

    public void setDespawnTimer(int var1);
}

