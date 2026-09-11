/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.Nameable;
import org.bukkit.block.Lockable;
import org.bukkit.block.TileState;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface Container
extends TileState,
BlockInventoryHolder,
Lockable,
Nameable {
    @Override
    @NotNull
    public Inventory getInventory();

    @NotNull
    public Inventory getSnapshotInventory();
}

