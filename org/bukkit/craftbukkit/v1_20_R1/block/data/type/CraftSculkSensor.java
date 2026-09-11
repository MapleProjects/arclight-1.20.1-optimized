/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSculkSensor
extends CraftBlockData
implements SculkSensor {
    private static final EnumProperty<?> PHASE = CraftSculkSensor.getEnum("sculk_sensor_phase");

    @Override
    public SculkSensor.Phase getPhase() {
        return this.get(PHASE, SculkSensor.Phase.class);
    }

    @Override
    public void setPhase(SculkSensor.Phase phase) {
        this.set(PHASE, phase);
    }
}

