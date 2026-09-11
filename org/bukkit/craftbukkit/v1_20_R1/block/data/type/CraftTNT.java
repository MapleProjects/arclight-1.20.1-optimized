/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.TNT;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftTNT
extends CraftBlockData
implements TNT {
    private static final BooleanProperty UNSTABLE = CraftTNT.getBoolean("unstable");

    @Override
    public boolean isUnstable() {
        return (Boolean)this.get(UNSTABLE);
    }

    @Override
    public void setUnstable(boolean unstable) {
        this.set(UNSTABLE, unstable);
    }
}

