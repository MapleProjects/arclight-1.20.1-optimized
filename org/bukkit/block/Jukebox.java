/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.Material;
import org.bukkit.block.TileState;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.JukeboxInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Jukebox
extends TileState,
BlockInventoryHolder {
    @NotNull
    public Material getPlaying();

    public void setPlaying(@Nullable Material var1);

    public boolean hasRecord();

    @NotNull
    public ItemStack getRecord();

    public void setRecord(@Nullable ItemStack var1);

    public boolean isPlaying();

    public boolean startPlaying();

    public void stopPlaying();

    public boolean eject();

    @Override
    @NotNull
    public JukeboxInventory getInventory();

    @NotNull
    public JukeboxInventory getSnapshotInventory();
}

