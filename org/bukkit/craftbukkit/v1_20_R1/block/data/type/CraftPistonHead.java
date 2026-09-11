/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftPistonHead
extends CraftBlockData
implements PistonHead {
    private static final BooleanProperty SHORT = CraftPistonHead.getBoolean("short");

    @Override
    public boolean isShort() {
        return (Boolean)this.get(SHORT);
    }

    @Override
    public void setShort(boolean _short) {
        this.set(SHORT, _short);
    }
}

