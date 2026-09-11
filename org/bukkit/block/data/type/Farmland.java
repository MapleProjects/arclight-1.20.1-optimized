/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface Farmland
extends BlockData {
    public int getMoisture();

    public void setMoisture(int var1);

    public int getMaximumMoisture();
}

