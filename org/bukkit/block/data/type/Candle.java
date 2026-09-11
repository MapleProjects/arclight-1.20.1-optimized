/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.Waterlogged;

public interface Candle
extends Lightable,
Waterlogged {
    public int getCandles();

    public void setCandles(int var1);

    public int getMaximumCandles();
}

