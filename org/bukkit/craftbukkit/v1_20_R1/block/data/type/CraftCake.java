/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Cake;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftCake
extends CraftBlockData
implements Cake {
    private static final IntegerProperty BITES = CraftCake.getInteger("bites");

    @Override
    public int getBites() {
        return (Integer)this.get(BITES);
    }

    @Override
    public void setBites(int bites) {
        this.set(BITES, bites);
    }

    @Override
    public int getMaximumBites() {
        return CraftCake.getMax(BITES);
    }
}

