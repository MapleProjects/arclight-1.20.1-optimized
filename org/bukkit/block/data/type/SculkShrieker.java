/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

public interface SculkShrieker
extends Waterlogged {
    public boolean isCanSummon();

    public void setCanSummon(boolean var1);

    public boolean isShrieking();

    public void setShrieking(boolean var1);
}

