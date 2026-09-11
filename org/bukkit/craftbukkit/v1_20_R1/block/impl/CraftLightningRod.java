/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.LightningRodBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.LightningRod;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftLightningRod
extends CraftBlockData
implements LightningRod,
Directional,
Powerable,
Waterlogged {
    private static final EnumProperty<?> FACING = CraftLightningRod.getEnum(LightningRodBlock.class, "facing");
    private static final BooleanProperty POWERED = CraftLightningRod.getBoolean(LightningRodBlock.class, "powered");
    private static final BooleanProperty WATERLOGGED = CraftLightningRod.getBoolean(LightningRodBlock.class, "waterlogged");

    public CraftLightningRod() {
    }

    public CraftLightningRod(BlockState state) {
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

