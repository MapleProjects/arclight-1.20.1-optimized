/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

public interface RespawnAnchor
extends BlockData {
    public int getCharges();

    public void setCharges(int var1);

    public int getMaximumCharges();
}

