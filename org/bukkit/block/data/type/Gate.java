/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;

public interface Gate
extends Directional,
Openable,
Powerable {
    public boolean isInWall();

    public void setInWall(boolean var1);
}

