/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.StairBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftStairs
extends CraftBlockData
implements Stairs,
Bisected,
Directional,
Waterlogged {
    private static final EnumProperty<?> SHAPE = CraftStairs.getEnum(StairBlock.class, "shape");
    private static final EnumProperty<?> HALF = CraftStairs.getEnum(StairBlock.class, "half");
    private static final EnumProperty<?> FACING = CraftStairs.getEnum(StairBlock.class, "facing");
    private static final BooleanProperty WATERLOGGED = CraftStairs.getBoolean(StairBlock.class, "waterlogged");

    public CraftStairs() {
    }

    public CraftStairs(BlockState state) {
        super(state);
    }

    @Override
    public Stairs.Shape getShape() {
        return this.get(SHAPE, Stairs.Shape.class);
    }

    @Override
    public void setShape(Stairs.Shape shape) {
        this.set(SHAPE, shape);
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

