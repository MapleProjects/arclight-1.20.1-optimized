/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data;

import org.bukkit.block.data.BlockData;

public interface Levelled
extends BlockData {
    public int getLevel();

    public void setLevel(int var1);

    public int getMaximumLevel();
}

