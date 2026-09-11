/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Brushable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftBrushable
extends CraftBlockData
implements Brushable {
    private static final IntegerProperty DUSTED = CraftBrushable.getInteger("dusted");

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

