/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.DetectorRailBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.DetectorRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.RedstoneRail;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftMinecartDetector
extends CraftBlockData
implements RedstoneRail,
Powerable,
Rail,
Waterlogged {
    private static final BooleanProperty POWERED = CraftMinecartDetector.getBoolean(DetectorRailBlock.class, "powered");
    private static final EnumProperty<?> SHAPE = CraftMinecartDetector.getEnum(DetectorRailBlock.class, "shape");
    private static final BooleanProperty WATERLOGGED = CraftMinecartDetector.getBoolean(DetectorRailBlock.class, "waterlogged");

    public CraftMinecartDetector() {
    }

    public CraftMinecartDetector(BlockState state) {
        super(state);
    }

    @Override
    public boolean isPowered() {
        return (Boolean)this.get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        this.set(POWERED, powered);
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

