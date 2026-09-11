/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data;

import org.bukkit.block.data.BlockData;

public interface Ageable
extends BlockData {
    public int getAge();

    public void setAge(int var1);

    public int getMaximumAge();
}

