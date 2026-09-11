/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

public interface Dispenser
extends Directional {
    public boolean isTriggered();

    public void setTriggered(boolean var1);
}

