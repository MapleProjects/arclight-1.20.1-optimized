/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;

public interface Repeater
extends Directional,
Powerable {
    public int getDelay();

    public void setDelay(int var1);

    public int getMinimumDelay();

    public int getMaximumDelay();

    public boolean isLocked();

    public void setLocked(boolean var1);
}

