/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SlabBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Slab;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftStepAbstract
extends CraftBlockData
implements Slab,
Waterlogged {
    private static final EnumProperty<?> TYPE = CraftStepAbstract.getEnum(SlabBlock.class, "type");
    private static final BooleanProperty WATERLOGGED = CraftStepAbstract.getBoolean(SlabBlock.class, "waterlogged");

    public CraftStepAbstract() {
    }

    public CraftStepAbstract(BlockState state) {
        super(state);
    }

    @Override
    public Slab.Type getType() {
        return this.get(TYPE, Slab.Type.class);
    }

    @Override
    public void setType(Slab.Type type) {
        this.set(TYPE, type);
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

