/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.PointedDripstoneBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftPointedDripstone
extends CraftBlockData
implements PointedDripstone,
Waterlogged {
    private static final EnumProperty<?> VERTICAL_DIRECTION = CraftPointedDripstone.getEnum(PointedDripstoneBlock.class, "vertical_direction");
    private static final EnumProperty<?> THICKNESS = CraftPointedDripstone.getEnum(PointedDripstoneBlock.class, "thickness");
    private static final BooleanProperty WATERLOGGED = CraftPointedDripstone.getBoolean(PointedDripstoneBlock.class, "waterlogged");

    public CraftPointedDripstone() {
    }

    public CraftPointedDripstone(BlockState state) {
        super(state);
    }

    @Override
    public BlockFace getVerticalDirection() {
        return this.get(VERTICAL_DIRECTION, BlockFace.class);
    }

    @Override
    public void setVerticalDirection(BlockFace direction) {
        this.set(VERTICAL_DIRECTION, direction);
    }

    @Override
    public Set<BlockFace> getVerticalDirections() {
        return this.getValues(VERTICAL_DIRECTION, BlockFace.class);
    }

    @Override
    public PointedDripstone.Thickness getThickness() {
        return this.get(THICKNESS, PointedDripstone.Thickness.class);
    }

    @Override
    public void setThickness(PointedDripstone.Thickness thickness) {
        this.set(THICKNESS, thickness);
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

