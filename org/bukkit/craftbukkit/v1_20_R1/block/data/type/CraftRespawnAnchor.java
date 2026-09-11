/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftRespawnAnchor
extends CraftBlockData
implements RespawnAnchor {
    private static final IntegerProperty CHARGES = CraftRespawnAnchor.getInteger("charges");

    @Override
    public int getCharges() {
        return (Integer)this.get(CHARGES);
    }

    @Override
    public void setCharges(int charges) {
        this.set(CHARGES, charges);
    }

    @Override
    public int getMaximumCharges() {
        return CraftRespawnAnchor.getMax(CHARGES);
    }
}

