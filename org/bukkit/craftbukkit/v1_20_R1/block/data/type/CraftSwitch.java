/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Switch;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftSwitch
extends CraftBlockData
implements Switch {
    private static final EnumProperty<?> FACE = CraftSwitch.getEnum("face");

    @Override
    public Switch.Face getFace() {
        return this.get(FACE, Switch.Face.class);
    }

    @Override
    public void setFace(Switch.Face face) {
        this.set(FACE, face);
    }
}

