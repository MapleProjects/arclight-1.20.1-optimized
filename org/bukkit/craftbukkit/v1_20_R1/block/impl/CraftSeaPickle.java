/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SeaPickleBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.SeaPickle;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSeaPickle
extends CraftBlockData
implements SeaPickle,
Waterlogged {
    private static final IntegerProperty PICKLES = CraftSeaPickle.getInteger(SeaPickleBlock.class, "pickles");
    private static final BooleanProperty WATERLOGGED = CraftSeaPickle.getBoolean(SeaPickleBlock.class, "waterlogged");

    public CraftSeaPickle() {
    }

    public CraftSeaPickle(BlockState state) {
        super(state);
    }

    @Override
    public int getPickles() {
        return (Integer)this.get(PICKLES);
    }

    @Override
    public void setPickles(int pickles) {
        this.set(PICKLES, pickles);
    }

    @Override
    public int getMinimumPickles() {
        return CraftSeaPickle.getMin(PICKLES);
    }

    @Override
    public int getMaximumPickles() {
        return CraftSeaPickle.getMax(PICKLES);
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

