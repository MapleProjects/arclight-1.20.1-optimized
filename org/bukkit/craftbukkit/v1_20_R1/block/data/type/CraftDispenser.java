/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftDispenser
extends CraftBlockData
implements Dispenser {
    private static final BooleanProperty TRIGGERED = CraftDispenser.getBoolean("triggered");

    @Override
    public boolean isTriggered() {
        return (Boolean)this.get(TRIGGERED);
    }

    @Override
    public void setTriggered(boolean triggered) {
        this.set(TRIGGERED, triggered);
    }
}

