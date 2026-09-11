/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.RailBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftMinecartTrack
extends CraftBlockData
implements Rail,
Waterlogged {
    private static final EnumProperty<?> SHAPE = CraftMinecartTrack.getEnum(RailBlock.class, "shape");
    private static final BooleanProperty WATERLOGGED = CraftMinecartTrack.getBoolean(RailBlock.class, "waterlogged");

    public CraftMinecartTrack() {
    }

    public CraftMinecartTrack(BlockState state) {
        super(state);
    }

    @Override
    public Rail.Shape getShape() {
        return this.get(SHAPE, Rail.Shape.class);
    }

    @Override
    public void setShape(Rail.Shape shape) {
        this.set(SHAPE, shape);
    }

    @Override
    public Set<Rail.Shape> getShapes() {
        return this.getValues(SHAPE, Rail.Shape.class);
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

