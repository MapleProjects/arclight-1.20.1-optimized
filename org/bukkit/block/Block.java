/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import java.util.Collection;
import org.bukkit.Chunk;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Translatable;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.Metadatable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.bukkit.util.VoxelShape;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Block
extends Metadatable,
Translatable {
    @Deprecated
    public byte getData();

    @NotNull
    public BlockData getBlockData();

    @NotNull
    public Block getRelative(int var1, int var2, int var3);

    @NotNull
    public Block getRelative(@NotNull BlockFace var1);

    @NotNull
    public Block getRelative(@NotNull BlockFace var1, int var2);

    @NotNull
    public Material getType();

    public byte getLightLevel();

    public byte getLightFromSky();

    public byte getLightFromBlocks();

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

    public void setBlockData(@NotNull BlockData var1);

    public void setBlockData(@NotNull BlockData var1, boolean var2);

    public void setType(@NotNull Material var1);

    public void setType(@NotNull Material var1, boolean var2);

    @Nullable
    public BlockFace getFace(@NotNull Block var1);

    @NotNull
    public BlockState getState();

    @NotNull
    public Biome getBiome();

    public void setBiome(@NotNull Biome var1);

    public boolean isBlockPowered();

    public boolean isBlockIndirectlyPowered();

    public boolean isBlockFacePowered(@NotNull BlockFace var1);

    public boolean isBlockFaceIndirectlyPowered(@NotNull BlockFace var1);

    public int getBlockPower(@NotNull BlockFace var1);

    public int getBlockPower();

    public boolean isEmpty();

    public boolean isLiquid();

    public double getTemperature();

    public double getHumidity();

    @NotNull
    public PistonMoveReaction getPistonMoveReaction();

    public boolean breakNaturally();

    public boolean breakNaturally(@Nullable ItemStack var1);

    public boolean applyBoneMeal(@NotNull BlockFace var1);

    @NotNull
    public Collection<ItemStack> getDrops();

    @NotNull
    public Collection<ItemStack> getDrops(@Nullable ItemStack var1);

    @NotNull
    public Collection<ItemStack> getDrops(@NotNull ItemStack var1, @Nullable Entity var2);

    public boolean isPreferredTool(@NotNull ItemStack var1);

    public float getBreakSpeed(@NotNull Player var1);

    public boolean isPassable();

    @Nullable
    public RayTraceResult rayTrace(@NotNull Location var1, @NotNull Vector var2, double var3, @NotNull FluidCollisionMode var5);

    @NotNull
    public BoundingBox getBoundingBox();

    @NotNull
    public VoxelShape getCollisionShape();

    public boolean canPlace(@NotNull BlockData var1);
}

