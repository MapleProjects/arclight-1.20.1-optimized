/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.LeavesBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftLeaves
extends CraftBlockData
implements Leaves,
Waterlogged {
    private static final IntegerProperty DISTANCE = CraftLeaves.getInteger(LeavesBlock.class, "distance");
    private static final BooleanProperty PERSISTENT = CraftLeaves.getBoolean(LeavesBlock.class, "persistent");
    private static final BooleanProperty WATERLOGGED = CraftLeaves.getBoolean(LeavesBlock.class, "waterlogged");

    public CraftLeaves() {
    }

    public CraftLeaves(BlockState state) {
        super(state);
    }

    @Override
    public boolean isPersistent() {
        return (Boolean)this.get(PERSISTENT);
    }

    @Override
    public void setPersistent(boolean persistent) {
        this.set(PERSISTENT, persistent);
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
    public boolean isWaterlogged() {
        return (Boolean)this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}

