/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data;

import org.bukkit.block.data.BlockData;

public interface Powerable
extends BlockData {
    public boolean isPowered();

    public void setPowered(boolean var1);
}

