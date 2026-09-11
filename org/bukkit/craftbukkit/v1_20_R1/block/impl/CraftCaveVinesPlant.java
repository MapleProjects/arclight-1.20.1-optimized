/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CaveVinesPlantBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCaveVinesPlant
extends CraftBlockData
implements CaveVinesPlant {
    private static final BooleanProperty BERRIES = CraftCaveVinesPlant.getBoolean(CaveVinesPlantBlock.class, "berries");

    public CraftCaveVinesPlant() {
    }

    public CraftCaveVinesPlant(BlockState state) {
        super(state);
    }

    @Override
    public boolean isBerries() {
        return (Boolean)this.get(BERRIES);
    }

    @Override
    public void setBerries(boolean berries) {
        this.set(BERRIES, berries);
    }
}

