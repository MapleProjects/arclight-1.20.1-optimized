/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface Lectern
extends TileState,
BlockInventoryHolder {
    public int getPage();

    public void setPage(int var1);

    @Override
    @NotNull
    public Inventory getInventory();

    @NotNull
    public Inventory getSnapshotInventory();
}

