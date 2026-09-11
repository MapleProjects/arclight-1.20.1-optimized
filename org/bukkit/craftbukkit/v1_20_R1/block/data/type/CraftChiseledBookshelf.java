/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftChiseledBookshelf
extends CraftBlockData
implements ChiseledBookshelf {
    private static final BooleanProperty[] SLOT_OCCUPIED = new BooleanProperty[]{CraftChiseledBookshelf.getBoolean("slot_0_occupied"), CraftChiseledBookshelf.getBoolean("slot_1_occupied"), CraftChiseledBookshelf.getBoolean("slot_2_occupied"), CraftChiseledBookshelf.getBoolean("slot_3_occupied"), CraftChiseledBookshelf.getBoolean("slot_4_occupied"), CraftChiseledBookshelf.getBoolean("slot_5_occupied")};

    @Override
    public boolean isSlotOccupied(int slot) {
        return (Boolean)this.get(SLOT_OCCUPIED[slot]);
    }

    @Override
    public void setSlotOccupied(int slot, boolean has) {
        this.set(SLOT_OCCUPIED[slot], has);
    }

    @Override
    public Set<Integer> getOccupiedSlots() {
        ImmutableSet.Builder slots = ImmutableSet.builder();
        int index = 0;
        while (index < this.getMaximumOccupiedSlots()) {
            if (this.isSlotOccupied(index)) {
                slots.add((Object)index);
            }
            ++index;
        }
        return slots.build();
    }

    @Override
    public int getMaximumOccupiedSlots() {
        return SLOT_OCCUPIED.length;
    }
}

