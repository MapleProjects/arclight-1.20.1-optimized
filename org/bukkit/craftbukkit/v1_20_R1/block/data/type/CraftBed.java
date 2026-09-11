/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.data.type.Bed;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftBed
extends CraftBlockData
implements Bed {
    private static final EnumProperty<?> PART = CraftBed.getEnum("part");
    private static final BooleanProperty OCCUPIED = CraftBed.getBoolean("occupied");

    @Override
    public Bed.Part getPart() {
        return this.get(PART, Bed.Part.class);
    }

    @Override
    public void setPart(Bed.Part part) {
        this.set(PART, part);
    }

    @Override
    public boolean isOccupied() {
        return (Boolean)this.get(OCCUPIED);
    }
}

