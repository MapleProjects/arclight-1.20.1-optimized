/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CoralWallFanBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.CoralWallFanBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.CoralWallFan;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCoralFanWall
extends CraftBlockData
implements CoralWallFan,
Directional,
Waterlogged {
    private static final EnumProperty<?> FACING = CraftCoralFanWall.getEnum(CoralWallFanBlock.class, "facing");
    private static final BooleanProperty WATERLOGGED = CraftCoralFanWall.getBoolean(CoralWallFanBlock.class, "waterlogged");

    public CraftCoralFanWall() {
    }

    public CraftCoralFanWall(BlockState state) {
        super(state);
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

