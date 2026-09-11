/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Hatchable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftHatchable
extends CraftBlockData
implements Hatchable {
    private static final IntegerProperty HATCH = CraftHatchable.getInteger("hatch");

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
        return CraftHatchable.getMax(HATCH);
    }
}

