/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.SeaPickle;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSeaPickle
extends CraftBlockData
implements SeaPickle {
    private static final IntegerProperty PICKLES = CraftSeaPickle.getInteger("pickles");

    @Override
    public int getPickles() {
        return (Integer)this.get(PICKLES);
    }

    @Override
    public void setPickles(int pickles) {
        this.set(PICKLES, pickles);
    }

    @Override
    public int getMinimumPickles() {
        return CraftSeaPickle.getMin(PICKLES);
    }

    @Override
    public int getMaximumPickles() {
        return CraftSeaPickle.getMax(PICKLES);
    }
}

