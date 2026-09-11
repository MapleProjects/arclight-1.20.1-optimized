/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.WallBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Wall;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCobbleWall
extends CraftBlockData
implements Wall,
Waterlogged {
    private static final BooleanProperty UP = CraftCobbleWall.getBoolean(WallBlock.class, "up");
    private static final EnumProperty<?>[] HEIGHTS = new EnumProperty[]{CraftCobbleWall.getEnum(WallBlock.class, "north"), CraftCobbleWall.getEnum(WallBlock.class, "east"), CraftCobbleWall.getEnum(WallBlock.class, "south"), CraftCobbleWall.getEnum(WallBlock.class, "west")};
    private static final BooleanProperty WATERLOGGED = CraftCobbleWall.getBoolean(WallBlock.class, "waterlogged");

    public CraftCobbleWall() {
    }

    public CraftCobbleWall(BlockState state) {
        super(state);
    }

    @Override
    public boolean isUp() {
        return (Boolean)this.get(UP);
    }

    @Override
    public void setUp(boolean up) {
        this.set(UP, up);
    }

    @Override
    public Wall.Height getHeight(BlockFace face) {
        return this.get(HEIGHTS[face.ordinal()], Wall.Height.class);
    }

    @Override
    public void setHeight(BlockFace face, Wall.Height height) {
        this.set(HEIGHTS[face.ordinal()], height);
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

