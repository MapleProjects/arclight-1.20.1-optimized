/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

public interface Scaffolding
extends Waterlogged {
    public boolean isBottom();

    public void setBottom(boolean var1);

    public int getDistance();

    public void setDistance(int var1);

    public int getMaximumDistance();
}

