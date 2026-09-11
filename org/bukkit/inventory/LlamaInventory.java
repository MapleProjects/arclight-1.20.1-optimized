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

public interface LlamaInventory
extends AbstractHorseInventory {
    @Nullable
    public ItemStack getDecor();

    public void setDecor(@Nullable ItemStack var1);
}

