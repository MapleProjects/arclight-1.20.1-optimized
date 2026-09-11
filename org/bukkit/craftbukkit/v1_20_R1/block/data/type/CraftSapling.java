/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSapling
extends CraftBlockData
implements Sapling {
    private static final IntegerProperty STAGE = CraftSapling.getInteger("stage");

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

