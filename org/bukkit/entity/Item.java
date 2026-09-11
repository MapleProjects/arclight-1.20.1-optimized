/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.UUID;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Item
extends Entity {
    @NotNull
    public ItemStack getItemStack();

    public void setItemStack(@NotNull ItemStack var1);

    public int getPickupDelay();

    public void setPickupDelay(int var1);

    public void setUnlimitedLifetime(boolean var1);

    public boolean isUnlimitedLifetime();

    public void setOwner(@Nullable UUID var1);

    @Nullable
    public UUID getOwner();

    public void setThrower(@Nullable UUID var1);

    @Nullable
    public UUID getThrower();
}

