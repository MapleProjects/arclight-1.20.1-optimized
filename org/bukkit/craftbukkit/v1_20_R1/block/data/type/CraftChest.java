/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Chest;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftChest
extends CraftBlockData
implements Chest {
    private static final EnumProperty<?> TYPE = CraftChest.getEnum("type");

    @Override
    public Chest.Type getType() {
        return this.get(TYPE, Chest.Type.class);
    }

    @Override
    public void setType(Chest.Type type) {
        this.set(TYPE, type);
    }
}

