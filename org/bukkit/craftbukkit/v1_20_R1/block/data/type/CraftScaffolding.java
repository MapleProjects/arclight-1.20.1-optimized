/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Scaffolding;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftScaffolding
extends CraftBlockData
implements Scaffolding {
    private static final BooleanProperty BOTTOM = CraftScaffolding.getBoolean("bottom");
    private static final IntegerProperty DISTANCE = CraftScaffolding.getInteger("distance");

    @Override
    public boolean isBottom() {
        return (Boolean)this.get(BOTTOM);
    }

    @Override
    public void setBottom(boolean bottom) {
        this.set(BOTTOM, bottom);
    }

    @Override
    public int getDistance() {
        return (Integer)this.get(DISTANCE);
    }

    @Override
    public void setDistance(int distance) {
        this.set(DISTANCE, distance);
    }

    @Override
    public int getMaximumDistance() {
        return CraftScaffolding.getMax(DISTANCE);
    }
}

