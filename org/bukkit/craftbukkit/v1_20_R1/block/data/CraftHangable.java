/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Hangable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftHangable
extends CraftBlockData
implements Hangable {
    private static final BooleanProperty HANGING = CraftHangable.getBoolean("hanging");

    @Override
    public boolean isHanging() {
        return (Boolean)this.get(HANGING);
    }

    @Override
    public void setHanging(boolean hanging) {
        this.set(HANGING, hanging);
    }
}

