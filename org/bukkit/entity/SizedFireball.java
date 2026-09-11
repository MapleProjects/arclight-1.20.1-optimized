/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Fireball;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface SizedFireball
extends Fireball {
    @NotNull
    public ItemStack getDisplayItem();

    public void setDisplayItem(@NotNull ItemStack var1);
}

