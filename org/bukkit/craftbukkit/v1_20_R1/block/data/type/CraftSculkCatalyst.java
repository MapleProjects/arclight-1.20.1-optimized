/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.SculkCatalyst;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSculkCatalyst
extends CraftBlockData
implements SculkCatalyst {
    private static final BooleanProperty BLOOM = CraftSculkCatalyst.getBoolean("bloom");

    @Override
    public boolean isBloom() {
        return (Boolean)this.get(BLOOM);
    }

    @Override
    public void setBloom(boolean bloom) {
        this.set(BLOOM, bloom);
    }
}

