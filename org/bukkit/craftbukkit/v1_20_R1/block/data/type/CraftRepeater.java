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
import org.bukkit.block.data.type.Repeater;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftRepeater
extends CraftBlockData
implements Repeater {
    private static final IntegerProperty DELAY = CraftRepeater.getInteger("delay");
    private static final BooleanProperty LOCKED = CraftRepeater.getBoolean("locked");

    @Override
    public int getDelay() {
        return (Integer)this.get(DELAY);
    }

    @Override
    public void setDelay(int delay) {
        this.set(DELAY, delay);
    }

    @Override
    public int getMinimumDelay() {
        return CraftRepeater.getMin(DELAY);
    }

    @Override
    public int getMaximumDelay() {
        return CraftRepeater.getMax(DELAY);
    }

    @Override
    public boolean isLocked() {
        return (Boolean)this.get(LOCKED);
    }

    @Override
    public void setLocked(boolean locked) {
        this.set(LOCKED, locked);
    }
}

