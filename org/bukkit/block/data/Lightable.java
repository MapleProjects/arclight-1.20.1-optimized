/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data;

import org.bukkit.block.data.BlockData;

public interface Lightable
extends BlockData {
    public boolean isLit();

    public void setLit(boolean var1);
}

