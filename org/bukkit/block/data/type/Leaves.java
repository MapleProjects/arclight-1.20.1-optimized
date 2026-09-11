/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

public interface Leaves
extends Waterlogged {
    public boolean isPersistent();

    public void setPersistent(boolean var1);

    public int getDistance();

    public void setDistance(int var1);
}

