/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CampfireBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import java.util.Set;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCampfire
extends CraftBlockData
implements Campfire,
Directional,
Lightable,
Waterlogged {
    private static final BooleanProperty SIGNAL_FIRE = CraftCampfire.getBoolean(CampfireBlock.class, "signal_fire");
    private static final EnumProperty<?> FACING = CraftCampfire.getEnum(CampfireBlock.class, "facing");
    private static final BooleanProperty LIT = CraftCampfire.getBoolean(CampfireBlock.class, "lit");
    private static final BooleanProperty WATERLOGGED = CraftCampfire.getBoolean(CampfireBlock.class, "waterlogged");

    public CraftCampfire() {
    }

    public CraftCampfire(BlockState state) {
        super(state);
    }

    @Override
    public boolean isSignalFire() {
        return (Boolean)this.get(SIGNAL_FIRE);
    }

    @Override
    public void setSignalFire(boolean signalFire) {
        this.set(SIGNAL_FIRE, signalFire);
    }

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(BlockFace facing) {
        this.set(FACING, facing);
    }

    @Override
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }

    @Override
    public boolean isLit() {
        return (Boolean)this.get(LIT);
    }

    @Override
    public void setLit(boolean lit) {
        this.set(LIT, lit);
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

