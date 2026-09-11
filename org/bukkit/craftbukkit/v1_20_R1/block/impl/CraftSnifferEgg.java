/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SnifferEggBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.SnifferEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Hatchable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSnifferEgg
extends CraftBlockData
implements Hatchable {
    private static final IntegerProperty HATCH = CraftSnifferEgg.getInteger(SnifferEggBlock.class, "hatch");

    public CraftSnifferEgg() {
    }

    public CraftSnifferEgg(BlockState state) {
        super(state);
    }

    @Override
    public int getHatch() {
        return (Integer)this.get(HATCH);
    }

    @Override
    public void setHatch(int hatch) {
        this.set(HATCH, hatch);
    }

    @Override
    public int getMaximumHatch() {
        return CraftSnifferEgg.getMax(HATCH);
    }
}

