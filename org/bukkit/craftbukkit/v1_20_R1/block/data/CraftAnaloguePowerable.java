/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftAnaloguePowerable
extends CraftBlockData
implements AnaloguePowerable {
    private static final IntegerProperty POWER = CraftAnaloguePowerable.getInteger("power");

    @Override
    public int getPower() {
        return (Integer)this.get(POWER);
    }

    @Override
    public void setPower(int power) {
        this.set(POWER, power);
    }

    @Override
    public int getMaximumPower() {
        return CraftAnaloguePowerable.getMax(POWER);
    }
}

