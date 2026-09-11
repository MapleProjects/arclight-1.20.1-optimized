/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.DaylightDetector;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftDaylightDetector
extends CraftBlockData
implements DaylightDetector {
    private static final BooleanProperty INVERTED = CraftDaylightDetector.getBoolean("inverted");

    @Override
    public boolean isInverted() {
        return (Boolean)this.get(INVERTED);
    }

    @Override
    public void setInverted(boolean inverted) {
        this.set(INVERTED, inverted);
    }
}

