/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SculkSensorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSculkSensor
extends CraftBlockData
implements SculkSensor,
AnaloguePowerable,
Waterlogged {
    private static final EnumProperty<?> PHASE = CraftSculkSensor.getEnum(SculkSensorBlock.class, "sculk_sensor_phase");
    private static final IntegerProperty POWER = CraftSculkSensor.getInteger(SculkSensorBlock.class, "power");
    private static final BooleanProperty WATERLOGGED = CraftSculkSensor.getBoolean(SculkSensorBlock.class, "waterlogged");

    public CraftSculkSensor() {
    }

    public CraftSculkSensor(BlockState state) {
        super(state);
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
        return CraftSculkSensor.getMax(POWER);
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

