/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Hatchable;

public interface TurtleEgg
extends Hatchable {
    public int getEggs();

    public void setEggs(int var1);

    public int getMinimumEggs();

    public int getMaximumEggs();
}

