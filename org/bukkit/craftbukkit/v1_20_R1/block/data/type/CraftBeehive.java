/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftBeehive
extends CraftBlockData
implements Beehive {
    private static final IntegerProperty HONEY_LEVEL = CraftBeehive.getInteger("honey_level");

    @Override
    public int getHoneyLevel() {
        return (Integer)this.get(HONEY_LEVEL);
    }

    @Override
    public void setHoneyLevel(int honeyLevel) {
        this.set(HONEY_LEVEL, honeyLevel);
    }

    @Override
    public int getMaximumHoneyLevel() {
        return CraftBeehive.getMax(HONEY_LEVEL);
    }
}

