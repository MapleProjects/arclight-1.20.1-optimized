/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Snow;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public class CraftSnow
extends CraftBlockData
implements Snow {
    private static final IntegerProperty LAYERS = CraftSnow.getInteger("layers");

    @Override
    public int getLayers() {
        return (Integer)this.get(LAYERS);
    }

    @Override
    public void setLayers(int layers) {
        this.set(LAYERS, layers);
    }

    @Override
    public int getMinimumLayers() {
        return CraftSnow.getMin(LAYERS);
    }

    @Override
    public int getMaximumLayers() {
        return CraftSnow.getMax(LAYERS);
    }
}

