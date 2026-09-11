/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.PinkPetals;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftPinkPetals
extends CraftBlockData
implements PinkPetals {
    private static final IntegerProperty FLOWER_AMOUNT = CraftPinkPetals.getInteger("flower_amount");

    @Override
    public int getFlowerAmount() {
        return (Integer)this.get(FLOWER_AMOUNT);
    }

    @Override
    public void setFlowerAmount(int flower_amount) {
        this.set(FLOWER_AMOUNT, flower_amount);
    }

    @Override
    public int getMaximumFlowerAmount() {
        return CraftPinkPetals.getMax(FLOWER_AMOUNT);
    }
}

