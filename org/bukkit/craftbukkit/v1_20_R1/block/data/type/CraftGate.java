/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.Gate;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftGate
extends CraftBlockData
implements Gate {
    private static final BooleanProperty IN_WALL = CraftGate.getBoolean("in_wall");

    @Override
    public boolean isInWall() {
        return (Boolean)this.get(IN_WALL);
    }

    @Override
    public void setInWall(boolean inWall) {
        this.set(IN_WALL, inWall);
    }
}

