/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftCampfire
extends CraftBlockData
implements Campfire {
    private static final BooleanProperty SIGNAL_FIRE = CraftCampfire.getBoolean("signal_fire");

    @Override
    public boolean isSignalFire() {
        return (Boolean)this.get(SIGNAL_FIRE);
    }

    @Override
    public void setSignalFire(boolean signalFire) {
        this.set(SIGNAL_FIRE, signalFire);
    }
}

