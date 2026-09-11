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
import org.bukkit.block.data.type.Leaves;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftLeaves
extends CraftBlockData
implements Leaves {
    private static final IntegerProperty DISTANCE = CraftLeaves.getInteger("distance");
    private static final BooleanProperty PERSISTENT = CraftLeaves.getBoolean("persistent");

    @Override
    public boolean isPersistent() {
        return (Boolean)this.get(PERSISTENT);
    }

    @Override
    public void setPersistent(boolean persistent) {
        this.set(PERSISTENT, persistent);
    }

    @Override
    public int getDistance() {
        return (Integer)this.get(DISTANCE);
    }

    @Override
    public void setDistance(int distance) {
        this.set(DISTANCE, distance);
    }
}

