/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftFarmland
extends CraftBlockData
implements Farmland {
    private static final IntegerProperty MOISTURE = CraftFarmland.getInteger("moisture");

    @Override
    public int getMoisture() {
        return (Integer)this.get(MOISTURE);
    }

    @Override
    public void setMoisture(int moisture) {
        this.set(MOISTURE, moisture);
    }

    @Override
    public int getMaximumMoisture() {
        return CraftFarmland.getMax(MOISTURE);
    }
}

