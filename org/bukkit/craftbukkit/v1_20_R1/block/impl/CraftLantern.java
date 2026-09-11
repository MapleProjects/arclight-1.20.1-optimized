/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.LanternBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Hangable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Lantern;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftLantern
extends CraftBlockData
implements Lantern,
Hangable,
Waterlogged {
    private static final BooleanProperty HANGING = CraftLantern.getBoolean(LanternBlock.class, "hanging");
    private static final BooleanProperty WATERLOGGED = CraftLantern.getBoolean(LanternBlock.class, "waterlogged");

    public CraftLantern() {
    }

    public CraftLantern(BlockState state) {
        super(state);
    }

    @Override
    public boolean isHanging() {
        return (Boolean)this.get(HANGING);
    }

    @Override
    public void setHanging(boolean hanging) {
        this.set(HANGING, hanging);
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

