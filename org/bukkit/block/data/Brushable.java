/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data;

import org.bukkit.block.data.BlockData;

public interface Brushable
extends BlockData {
    public int getDusted();

    public void setDusted(int var1);

    public int getMaximumDusted();
}

