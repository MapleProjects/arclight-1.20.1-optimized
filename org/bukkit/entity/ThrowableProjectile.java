/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ThrowableProjectile
extends Projectile {
    @NotNull
    public ItemStack getItem();

    public void setItem(@NotNull ItemStack var1);
}

