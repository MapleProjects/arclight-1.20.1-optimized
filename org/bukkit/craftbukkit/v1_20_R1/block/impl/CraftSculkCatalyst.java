/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SculkCatalystBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.SculkCatalyst;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSculkCatalyst
extends CraftBlockData
implements SculkCatalyst {
    private static final BooleanProperty BLOOM = CraftSculkCatalyst.getBoolean(SculkCatalystBlock.class, "bloom");

    public CraftSculkCatalyst() {
    }

    public CraftSculkCatalyst(BlockState state) {
        super(state);
    }

    @Override
    public boolean isBloom() {
        return (Boolean)this.get(BLOOM);
    }

    @Override
    public void setBloom(boolean bloom) {
        this.set(BLOOM, bloom);
    }
}

