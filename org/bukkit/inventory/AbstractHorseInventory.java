/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface AbstractHorseInventory
extends Inventory {
    @Nullable
    public ItemStack getSaddle();

    public void setSaddle(@Nullable ItemStack var1);
}

