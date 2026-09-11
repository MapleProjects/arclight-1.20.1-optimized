/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Rotation;
import org.bukkit.entity.Hanging;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemFrame
extends Hanging {
    @NotNull
    public ItemStack getItem();

    public void setItem(@Nullable ItemStack var1);

    public void setItem(@Nullable ItemStack var1, boolean var2);

    public float getItemDropChance();

    public void setItemDropChance(float var1);

    @NotNull
    public Rotation getRotation();

    public void setRotation(@NotNull Rotation var1) throws IllegalArgumentException;

    public boolean isVisible();

    public void setVisible(boolean var1);

    public boolean isFixed();

    public void setFixed(boolean var1);
}

