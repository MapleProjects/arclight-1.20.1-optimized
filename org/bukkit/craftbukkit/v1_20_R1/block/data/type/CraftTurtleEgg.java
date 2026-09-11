/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftTurtleEgg
extends CraftBlockData
implements TurtleEgg {
    private static final IntegerProperty EGGS = CraftTurtleEgg.getInteger("eggs");

    @Override
    public int getEggs() {
        return (Integer)this.get(EGGS);
    }

    @Override
    public void setEggs(int eggs) {
        this.set(EGGS, eggs);
    }

    @Override
    public int getMinimumEggs() {
        return CraftTurtleEgg.getMin(EGGS);
    }

    @Override
    public int getMaximumEggs() {
        return CraftTurtleEgg.getMax(EGGS);
    }
}

