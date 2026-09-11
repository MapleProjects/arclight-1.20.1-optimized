/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Hopper;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftHopper
extends CraftBlockData
implements Hopper {
    private static final BooleanProperty ENABLED = CraftHopper.getBoolean("enabled");

    @Override
    public boolean isEnabled() {
        return (Boolean)this.get(ENABLED);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.set(ENABLED, enabled);
    }
}

