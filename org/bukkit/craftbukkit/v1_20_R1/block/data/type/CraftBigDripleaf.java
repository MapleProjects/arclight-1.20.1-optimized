/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.BigDripleaf;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftBigDripleaf
extends CraftBlockData
implements BigDripleaf {
    private static final EnumProperty<?> TILT = CraftBigDripleaf.getEnum("tilt");

    @Override
    public BigDripleaf.Tilt getTilt() {
        return this.get(TILT, BigDripleaf.Tilt.class);
    }

    @Override
    public void setTilt(BigDripleaf.Tilt tilt) {
        this.set(TILT, tilt);
    }
}

