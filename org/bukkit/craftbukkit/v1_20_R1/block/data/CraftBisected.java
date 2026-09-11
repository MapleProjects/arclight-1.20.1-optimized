/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.Bisected;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public class CraftBisected
extends CraftBlockData
implements Bisected {
    private static final EnumProperty<?> HALF = CraftBisected.getEnum("half");

    @Override
    public Bisected.Half getHalf() {
        return this.get(HALF, Bisected.Half.class);
    }

    @Override
    public void setHalf(Bisected.Half half) {
        this.set(HALF, half);
    }
}

