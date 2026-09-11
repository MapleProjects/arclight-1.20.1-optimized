/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Levelled;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftLevelled
extends CraftBlockData
implements Levelled {
    private static final IntegerProperty LEVEL = CraftLevelled.getInteger("level");

    @Override
    public int getLevel() {
        return (Integer)this.get(LEVEL);
    }

    @Override
    public void setLevel(int level) {
        this.set(LEVEL, level);
    }

    @Override
    public int getMaximumLevel() {
        return CraftLevelled.getMax(LEVEL);
    }
}

