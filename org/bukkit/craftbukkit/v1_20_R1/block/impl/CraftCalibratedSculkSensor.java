/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CalibratedSculkSensorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.CalibratedSculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.CalibratedSculkSensor;
import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCalibratedSculkSensor
extends CraftBlockData
implements CalibratedSculkSensor,
Directional,
SculkSensor,
AnaloguePowerable,
Waterlogged {
    private static final EnumProperty<?> FACING = CraftCalibratedSculkSensor.getEnum(CalibratedSculkSensorBlock.class, "facing");
    private static final EnumProperty<?> PHASE = CraftCalibratedSculkSensor.getEnum(CalibratedSculkSensorBlock.class, "sculk_sensor_phase");
    private static final IntegerProperty POWER = CraftCalibratedSculkSensor.getInteger(CalibratedSculkSensorBlock.class, "power");
    private static final BooleanProperty WATERLOGGED = CraftCalibratedSculkSensor.getBoolean(CalibratedSculkSensorBlock.class, "waterlogged");

    public CraftCalibratedSculkSensor() {
    }

    public CraftCalibratedSculkSensor(BlockState state) {
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
    public SculkSensor.Phase getPhase() {
        return this.get(PHASE, SculkSensor.Phase.class);
    }

    @Override
    public void setPhase(SculkSensor.Phase phase) {
        this.set(PHASE, phase);
    }

    @Override
    public int getPower() {
        return (Integer)this.get(POWER);
    }

    @Override
    public void setPower(int power) {
        this.set(POWER, power);
    }

    @Override
    public int getMaximumPower() {
        return CraftCalibratedSculkSensor.getMax(POWER);
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

