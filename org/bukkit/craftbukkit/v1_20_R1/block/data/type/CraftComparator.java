/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftComparator
extends CraftBlockData
implements Comparator {
    private static final EnumProperty<?> MODE = CraftComparator.getEnum("mode");

    @Override
    public Comparator.Mode getMode() {
        return this.get(MODE, Comparator.Mode.class);
    }

    @Override
    public void setMode(Comparator.Mode mode) {
        this.set(MODE, mode);
    }
}

