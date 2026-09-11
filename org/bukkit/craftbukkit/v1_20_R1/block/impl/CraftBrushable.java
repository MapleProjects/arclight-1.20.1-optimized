/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.BrushableBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Brushable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftBrushable
extends CraftBlockData
implements Brushable {
    private static final IntegerProperty DUSTED = CraftBrushable.getInteger(BrushableBlock.class, "dusted");

    public CraftBrushable() {
    }

    public CraftBrushable(BlockState state) {
        super(state);
    }

    @Override
    public int getDusted() {
        return (Integer)this.get(DUSTED);
    }

    @Override
    public void setDusted(int dusted) {
        this.set(DUSTED, dusted);
    }

    @Override
    public int getMaximumDusted() {
        return CraftBrushable.getMax(DUSTED);
    }
}

