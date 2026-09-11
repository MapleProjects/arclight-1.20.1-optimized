/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.CandleBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.Candle;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftCandle
extends CraftBlockData
implements Candle,
Lightable,
Waterlogged {
    private static final IntegerProperty CANDLES = CraftCandle.getInteger(CandleBlock.class, "candles");
    private static final BooleanProperty LIT = CraftCandle.getBoolean(CandleBlock.class, "lit");
    private static final BooleanProperty WATERLOGGED = CraftCandle.getBoolean(CandleBlock.class, "waterlogged");

    public CraftCandle() {
    }

    public CraftCandle(BlockState state) {
        super(state);
    }

    @Override
    public int getCandles() {
        return (Integer)this.get(CANDLES);
    }

    @Override
    public void setCandles(int candles) {
        this.set(CANDLES, candles);
    }

    @Override
    public int getMaximumCandles() {
        return CraftCandle.getMax(CANDLES);
    }

    @Override
    public boolean isLit() {
        return (Boolean)this.get(LIT);
    }

    @Override
    public void setLit(boolean lit) {
        this.set(LIT, lit);
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

