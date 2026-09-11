/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SaplingBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSapling
extends CraftBlockData
implements Sapling {
    private static final IntegerProperty STAGE = CraftSapling.getInteger(SaplingBlock.class, "stage");

    public CraftSapling() {
    }

    public CraftSapling(BlockState state) {
        super(state);
    }

    @Override
    public int getStage() {
        return (Integer)this.get(STAGE);
    }

    @Override
    public void setStage(int stage) {
        this.set(STAGE, stage);
    }

    @Override
    public int getMaximumStage() {
        return CraftSapling.getMax(STAGE);
    }
}

