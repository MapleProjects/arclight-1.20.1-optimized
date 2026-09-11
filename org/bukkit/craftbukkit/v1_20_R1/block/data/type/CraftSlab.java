/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Slab;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSlab
extends CraftBlockData
implements Slab {
    private static final EnumProperty<?> TYPE = CraftSlab.getEnum("type");

    @Override
    public Slab.Type getType() {
        return this.get(TYPE, Slab.Type.class);
    }

    @Override
    public void setType(Slab.Type type) {
        this.set(TYPE, type);
    }
}

