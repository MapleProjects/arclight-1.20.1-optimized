/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Piston;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftPiston
extends CraftBlockData
implements Piston {
    private static final BooleanProperty EXTENDED = CraftPiston.getBoolean("extended");

    @Override
    public boolean isExtended() {
        return (Boolean)this.get(EXTENDED);
    }

    @Override
    public void setExtended(boolean extended) {
        this.set(EXTENDED, extended);
    }
}

