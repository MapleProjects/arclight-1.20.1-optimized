/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.block.DoubleChest;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DoubleChestInventory
extends Inventory {
    @NotNull
    public Inventory getLeftSide();

    @NotNull
    public Inventory getRightSide();

    @Override
    @Nullable
    public DoubleChest getHolder();
}

