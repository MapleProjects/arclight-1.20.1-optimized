/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.FarmBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSoil
extends CraftBlockData
implements Farmland {
    private static final IntegerProperty MOISTURE = CraftSoil.getInteger(FarmBlock.class, "moisture");

    public CraftSoil() {
    }

    public CraftSoil(BlockState state) {
        super(state);
    }

    @Override
    public int getMoisture() {
        return (Integer)this.get(MOISTURE);
    }

    @Override
    public void setMoisture(int moisture) {
        this.set(MOISTURE, moisture);
    }

    @Override
    public int getMaximumMoisture() {
        return CraftSoil.getMax(MOISTURE);
    }
}

