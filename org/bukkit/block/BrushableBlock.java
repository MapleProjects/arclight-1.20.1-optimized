/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.Nullable;

public interface BrushableBlock
extends Lootable,
TileState {
    @Nullable
    public ItemStack getItem();

    public void setItem(@Nullable ItemStack var1);
}

