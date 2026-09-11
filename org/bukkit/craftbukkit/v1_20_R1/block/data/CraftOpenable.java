/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Openable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftOpenable
extends CraftBlockData
implements Openable {
    private static final BooleanProperty OPEN = CraftOpenable.getBoolean("open");

    @Override
    public boolean isOpen() {
        return (Boolean)this.get(OPEN);
    }

    @Override
    public void setOpen(boolean open) {
        this.set(OPEN, open);
    }
}

