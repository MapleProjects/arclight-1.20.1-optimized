/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.MangroveLeavesBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.MangroveLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftMangroveLeaves
extends CraftBlockData
implements Leaves,
Waterlogged {
    private static final IntegerProperty DISTANCE = CraftMangroveLeaves.getInteger(MangroveLeavesBlock.class, "distance");
    private static final BooleanProperty PERSISTENT = CraftMangroveLeaves.getBoolean(MangroveLeavesBlock.class, "persistent");
    private static final BooleanProperty WATERLOGGED = CraftMangroveLeaves.getBoolean(MangroveLeavesBlock.class, "waterlogged");

    public CraftMangroveLeaves() {
    }

    public CraftMangroveLeaves(BlockState state) {
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

