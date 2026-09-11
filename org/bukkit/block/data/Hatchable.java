/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data;

import org.bukkit.block.data.BlockData;

public interface Hatchable
extends BlockData {
    public int getHatch();

    public void setHatch(int var1);

    public int getMaximumHatch();
}

