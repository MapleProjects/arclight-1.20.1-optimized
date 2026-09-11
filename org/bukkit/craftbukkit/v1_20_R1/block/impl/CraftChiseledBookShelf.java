/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  net.minecraft.world.level.block.ChiseledBookShelfBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftChiseledBookShelf
extends CraftBlockData
implements ChiseledBookshelf,
Directional {
    private static final BooleanProperty[] SLOT_OCCUPIED = new BooleanProperty[]{CraftChiseledBookShelf.getBoolean(ChiseledBookShelfBlock.class, "slot_0_occupied"), CraftChiseledBookShelf.getBoolean(ChiseledBookShelfBlock.class, "slot_1_occupied"), CraftChiseledBookShelf.getBoolean(ChiseledBookShelfBlock.class, "slot_2_occupied"), CraftChiseledBookShelf.getBoolean(ChiseledBookShelfBlock.class, "slot_3_occupied"), CraftChiseledBookShelf.getBoolean(ChiseledBookShelfBlock.class, "slot_4_occupied"), CraftChiseledBookShelf.getBoolean(ChiseledBookShelfBlock.class, "slot_5_occupied")};
    private static final EnumProperty<?> FACING = CraftChiseledBookShelf.getEnum(ChiseledBookShelfBlock.class, "facing");

    public CraftChiseledBookShelf() {
    }

    public CraftChiseledBookShelf(BlockState state) {
        super(state);
    }

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

    @Override
    public BlockFace getFacing() {
        return this.get(FACING, BlockFace.class);
    }

    @Override
    public void setFacing(BlockFace facing) {
        this.set(FACING, facing);
    }

    @Override
    public Set<BlockFace> getFaces() {
        return this.getValues(FACING, BlockFace.class);
    }
}

