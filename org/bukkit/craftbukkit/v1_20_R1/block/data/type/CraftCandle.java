/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data.type;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.bukkit.block.data.type.Candle;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftCandle
extends CraftBlockData
implements Candle {
    private static final IntegerProperty CANDLES = CraftCandle.getInteger("candles");

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
}

