/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface HorseInventory
extends AbstractHorseInventory {
    @Nullable
    public ItemStack getArmor();

    public void setArmor(@Nullable ItemStack var1);
}

