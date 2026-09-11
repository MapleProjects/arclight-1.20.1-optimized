/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

public interface SeaPickle
extends Waterlogged {
    public int getPickles();

    public void setPickles(int var1);

    public int getMinimumPickles();

    public int getMaximumPickles();
}

