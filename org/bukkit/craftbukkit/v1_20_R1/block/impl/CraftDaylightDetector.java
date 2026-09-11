/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.DaylightDetectorBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.type.DaylightDetector;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftDaylightDetector
extends CraftBlockData
implements DaylightDetector,
AnaloguePowerable {
    private static final BooleanProperty INVERTED = CraftDaylightDetector.getBoolean(DaylightDetectorBlock.class, "inverted");
    private static final IntegerProperty POWER = CraftDaylightDetector.getInteger(DaylightDetectorBlock.class, "power");

    public CraftDaylightDetector() {
    }

    public CraftDaylightDetector(BlockState state) {
        super(state);
    }

    @Override
    public boolean isInverted() {
        return (Boolean)this.get(INVERTED);
    }

    @Override
    public void setInverted(boolean inverted) {
        this.set(INVERTED, inverted);
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
        return CraftDaylightDetector.getMax(POWER);
    }
}

