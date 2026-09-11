/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface Snow
extends BlockData {
    public int getLayers();

    public void setLayers(int var1);

    public int getMinimumLayers();

    public int getMaximumLayers();
}

