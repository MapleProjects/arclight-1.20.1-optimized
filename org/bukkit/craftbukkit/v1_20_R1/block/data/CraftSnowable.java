/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Snowable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSnowable
extends CraftBlockData
implements Snowable {
    private static final BooleanProperty SNOWY = CraftSnowable.getBoolean("snowy");

    @Override
    public boolean isSnowy() {
        return (Boolean)this.get(SNOWY);
    }

    @Override
    public void setSnowy(boolean snowy) {
        this.set(SNOWY, snowy);
    }
}

