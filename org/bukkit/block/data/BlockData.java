/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SoundGroup;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.BlockSupport;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockData
extends Cloneable {
    @NotNull
    public Material getMaterial();

    @NotNull
    public String getAsString();

    @NotNull
    public String getAsString(boolean var1);

    @NotNull
    public BlockData merge(@NotNull BlockData var1);

    public boolean matches(@Nullable BlockData var1);

    @NotNull
    public BlockData clone();

    @NotNull
    public SoundGroup getSoundGroup();

    public int getLightEmission();

    public boolean isOccluding();

    public boolean requiresCorrectToolForDrops();

    public boolean isPreferredTool(@NotNull ItemStack var1);

    @NotNull
    public PistonMoveReaction getPistonMoveReaction();

    public boolean isSupported(@NotNull Block var1);

    public boolean isSupported(@NotNull Location var1);

    public boolean isFaceSturdy(@NotNull BlockFace var1, @NotNull BlockSupport var2);

    @NotNull
    public Material getPlacementMaterial();

    public void rotate(@NotNull StructureRotation var1);

    public void mirror(@NotNull Mirror var1);

    @NotNull
    @ApiStatus.Experimental
    public BlockState createBlockState();
}

