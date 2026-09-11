/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import java.util.Collection;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public interface ThrownPotion
extends ThrowableProjectile {
    @NotNull
    public Collection<PotionEffect> getEffects();

    @Override
    @NotNull
    public ItemStack getItem();

    @Override
    public void setItem(@NotNull ItemStack var1);
}

