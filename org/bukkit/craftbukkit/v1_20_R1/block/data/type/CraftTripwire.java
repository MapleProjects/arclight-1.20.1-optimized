/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftTripwire
extends CraftBlockData
implements Tripwire {
    private static final BooleanProperty DISARMED = CraftTripwire.getBoolean("disarmed");

    @Override
    public boolean isDisarmed() {
        return (Boolean)this.get(DISARMED);
    }

    @Override
    public void setDisarmed(boolean disarmed) {
        this.set(DISARMED, disarmed);
    }
}

