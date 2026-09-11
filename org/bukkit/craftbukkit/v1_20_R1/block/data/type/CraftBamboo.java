/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftBamboo
extends CraftBlockData
implements Bamboo {
    private static final EnumProperty<?> LEAVES = CraftBamboo.getEnum("leaves");

    @Override
    public Bamboo.Leaves getLeaves() {
        return this.get(LEAVES, Bamboo.Leaves.class);
    }

    @Override
    public void setLeaves(Bamboo.Leaves leaves) {
        this.set(LEAVES, leaves);
    }
}

