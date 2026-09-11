/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.BigDripleafBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.BigDripleaf;
import org.bukkit.block.data.type.Dripleaf;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftBigDripleaf
extends CraftBlockData
implements BigDripleaf,
Dripleaf,
Directional,
Waterlogged {
    private static final EnumProperty<?> TILT = CraftBigDripleaf.getEnum(BigDripleafBlock.class, "tilt");
    private static final EnumProperty<?> FACING = CraftBigDripleaf.getEnum(BigDripleafBlock.class, "facing");
    private static final BooleanProperty WATERLOGGED = CraftBigDripleaf.getBoolean(BigDripleafBlock.class, "waterlogged");

    public CraftBigDripleaf() {
    }

    public CraftBigDripleaf(BlockState state) {
        super(state);
    }

    @Override
    public BigDripleaf.Tilt getTilt() {
        return this.get(TILT, BigDripleaf.Tilt.class);
    }

    @Override
    public void setTilt(BigDripleaf.Tilt tilt) {
        this.set(TILT, tilt);
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

