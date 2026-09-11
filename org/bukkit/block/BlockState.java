/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.Metadatable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockState
extends Metadatable {
    @NotNull
    public Block getBlock();

    @NotNull
    public MaterialData getData();

    @NotNull
    public BlockData getBlockData();

    @NotNull
    public Material getType();

    public byte getLightLevel();

    @NotNull
    public World getWorld();

    public int getX();

    public int getY();

    public int getZ();

    @NotNull
    public Location getLocation();

    @Contract(value="null -> null; !null -> !null")
    @Nullable
    public Location getLocation(@Nullable Location var1);

    @NotNull
    public Chunk getChunk();

    public void setData(@NotNull MaterialData var1);

    public void setBlockData(@NotNull BlockData var1);

    public void setType(@NotNull Material var1);

    public boolean update();

    public boolean update(boolean var1);

    public boolean update(boolean var1, boolean var2);

    @Deprecated
    public byte getRawData();

    @Deprecated
    public void setRawData(byte var1);

    public boolean isPlaced();
}

