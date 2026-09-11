/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.ChiseledBookshelfInventory;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface ChiseledBookshelf
extends TileState,
BlockInventoryHolder {
    public int getLastInteractedSlot();

    public void setLastInteractedSlot(int var1);

    @Override
    @NotNull
    public ChiseledBookshelfInventory getInventory();

    @NotNull
    public ChiseledBookshelfInventory getSnapshotInventory();

    public int getSlot(@NotNull Vector var1);
}

