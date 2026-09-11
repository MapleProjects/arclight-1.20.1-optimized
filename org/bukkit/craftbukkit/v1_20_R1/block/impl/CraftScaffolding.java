/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.ScaffoldingBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Scaffolding;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftScaffolding
extends CraftBlockData
implements Scaffolding,
Waterlogged {
    private static final BooleanProperty BOTTOM = CraftScaffolding.getBoolean(ScaffoldingBlock.class, "bottom");
    private static final IntegerProperty DISTANCE = CraftScaffolding.getInteger(ScaffoldingBlock.class, "distance");
    private static final BooleanProperty WATERLOGGED = CraftScaffolding.getBoolean(ScaffoldingBlock.class, "waterlogged");

    public CraftScaffolding() {
    }

    public CraftScaffolding(BlockState state) {
        super(state);
    }

    @Override
    public boolean isBottom() {
        return (Boolean)this.get(BOTTOM);
    }

    @Override
    public void setBottom(boolean bottom) {
        this.set(BOTTOM, bottom);
    }

    @Override
    public int getDistance() {
        return (Integer)this.get(DISTANCE);
    }

    @Override
    public void setDistance(int distance) {
        this.set(DISTANCE, distance);
    }

    @Override
    public int getMaximumDistance() {
        return CraftScaffolding.getMax(DISTANCE);
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

