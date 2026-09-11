/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.SculkShriekerBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.SculkShrieker;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftSculkShrieker
extends CraftBlockData
implements SculkShrieker,
Waterlogged {
    private static final BooleanProperty CAN_SUMMON = CraftSculkShrieker.getBoolean(SculkShriekerBlock.class, "can_summon");
    private static final BooleanProperty SHRIEKING = CraftSculkShrieker.getBoolean(SculkShriekerBlock.class, "shrieking");
    private static final BooleanProperty WATERLOGGED = CraftSculkShrieker.getBoolean(SculkShriekerBlock.class, "waterlogged");

    public CraftSculkShrieker() {
    }

    public CraftSculkShrieker(BlockState state) {
        super(state);
    }

    @Override
    public boolean isCanSummon() {
        return (Boolean)this.get(CAN_SUMMON);
    }

    @Override
    public void setCanSummon(boolean can_summon) {
        this.set(CAN_SUMMON, can_summon);
    }

    @Override
    public boolean isShrieking() {
        return (Boolean)this.get(SHRIEKING);
    }

    @Override
    public void setShrieking(boolean shrieking) {
        this.set(SHRIEKING, shrieking);
    }

    @Override
    public boolean isWaterlogged() {
        return (Boolean)this.get(WATERLOGGED);
    }

    @Override
    public void setWaterlogged(boolean waterlogged) {
        this.set(WATERLOGGED, waterlogged);
    }
}

