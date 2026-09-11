/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SmallDripleafBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.SmallDripleafBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Dripleaf;
import org.bukkit.block.data.type.SmallDripleaf;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSmallDripleaf
extends CraftBlockData
implements SmallDripleaf,
Dripleaf,
Bisected,
Directional,
Waterlogged {
    private static final EnumProperty<?> HALF = CraftSmallDripleaf.getEnum(SmallDripleafBlock.class, "half");
    private static final EnumProperty<?> FACING = CraftSmallDripleaf.getEnum(SmallDripleafBlock.class, "facing");
    private static final BooleanProperty WATERLOGGED = CraftSmallDripleaf.getBoolean(SmallDripleafBlock.class, "waterlogged");

    public CraftSmallDripleaf() {
    }

    public CraftSmallDripleaf(BlockState state) {
        super(state);
    }

    @Override
    public Bisected.Half getHalf() {
        return this.get(HALF, Bisected.Half.class);
    }

    @Override
    public void setHalf(Bisected.Half half) {
        this.set(HALF, half);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(BlockFace facing) {
        this.set(FACING, facing);
    }

    @Override
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }

    @Override
    public boolean isWaterlogged() {
        return (Boolean)this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}

