/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

public interface Beehive
extends Directional {
    public int getHoneyLevel();

    public void setHoneyLevel(int var1);

    public int getMaximumHoneyLevel();
}

