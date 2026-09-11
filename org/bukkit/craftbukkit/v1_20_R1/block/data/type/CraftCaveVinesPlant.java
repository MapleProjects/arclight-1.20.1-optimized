/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftCaveVinesPlant
extends CraftBlockData
implements CaveVinesPlant {
    private static final BooleanProperty BERRIES = CraftCaveVinesPlant.getBoolean("berries");

    @Override
    public boolean isBerries() {
        return (Boolean)this.get(BERRIES);
    }

    @Override
    public void setBerries(boolean berries) {
        this.set(BERRIES, berries);
    }
}

