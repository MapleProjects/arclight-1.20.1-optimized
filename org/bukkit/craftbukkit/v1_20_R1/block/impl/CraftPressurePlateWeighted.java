/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.WeightedPressurePlateBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPressurePlateWeighted
extends CraftBlockData
implements AnaloguePowerable {
    private static final IntegerProperty POWER = CraftPressurePlateWeighted.getInteger(WeightedPressurePlateBlock.class, "power");

    public CraftPressurePlateWeighted() {
    }

    public CraftPressurePlateWeighted(BlockState state) {
        super(state);
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
        return CraftPressurePlateWeighted.getMax(POWER);
    }
}

