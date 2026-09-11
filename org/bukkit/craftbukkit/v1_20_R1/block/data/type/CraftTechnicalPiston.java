/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftTechnicalPiston
extends CraftBlockData
implements TechnicalPiston {
    private static final EnumProperty<?> TYPE = CraftTechnicalPiston.getEnum("type");

    @Override
    public TechnicalPiston.Type getType() {
        return this.get(TYPE, TechnicalPiston.Type.class);
    }

    @Override
    public void setType(TechnicalPiston.Type type) {
        this.set(TYPE, type);
    }
}

