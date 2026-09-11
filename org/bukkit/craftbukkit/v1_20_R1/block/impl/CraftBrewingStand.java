/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  net.minecraft.world.level.block.BrewingStandBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.BrewingStand;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftBrewingStand
extends CraftBlockData
implements BrewingStand {
    private static final BooleanProperty[] HAS_BOTTLE = new BooleanProperty[]{CraftBrewingStand.getBoolean(BrewingStandBlock.class, "has_bottle_0"), CraftBrewingStand.getBoolean(BrewingStandBlock.class, "has_bottle_1"), CraftBrewingStand.getBoolean(BrewingStandBlock.class, "has_bottle_2")};

    public CraftBrewingStand() {
    }

    public CraftBrewingStand(BlockState state) {
        super(state);
    }

    @Override
    public boolean hasBottle(int bottle) {
        return (Boolean)this.get(HAS_BOTTLE[bottle]);
    }

    @Override
    public void setBottle(int bottle, boolean has) {
        this.set(HAS_BOTTLE[bottle], has);
    }

    @Override
    public Set<Integer> getBottles() {
        ImmutableSet.Builder bottles = ImmutableSet.builder();
        int index = 0;
        while (index < this.getMaximumBottles()) {
            if (this.hasBottle(index)) {
                bottles.add((Object)index);
            }
            ++index;
        }
        return bottles.build();
    }

    @Override
    public int getMaximumBottles() {
        return HAS_BOTTLE.length;
    }
}

