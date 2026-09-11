/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.TrapDoorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftTrapdoor
extends CraftBlockData
implements TrapDoor,
Bisected,
Directional,
Openable,
Powerable,
Waterlogged {
    private static final EnumProperty<?> HALF = CraftTrapdoor.getEnum(TrapDoorBlock.class, "half");
    private static final EnumProperty<?> FACING = CraftTrapdoor.getEnum(TrapDoorBlock.class, "facing");
    private static final BooleanProperty OPEN = CraftTrapdoor.getBoolean(TrapDoorBlock.class, "open");
    private static final BooleanProperty POWERED = CraftTrapdoor.getBoolean(TrapDoorBlock.class, "powered");
    private static final BooleanProperty WATERLOGGED = CraftTrapdoor.getBoolean(TrapDoorBlock.class, "waterlogged");

    public CraftTrapdoor() {
    }

    public CraftTrapdoor(BlockState state) {
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
    public boolean isOpen() {
        return (Boolean)this.get(OPEN);
    }

    @Override
    public void setOpen(boolean open) {
        this.set(OPEN, open);
    }

    @Override
    public boolean isPowered() {
        return (Boolean)this.get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        this.set(POWERED, powered);
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

