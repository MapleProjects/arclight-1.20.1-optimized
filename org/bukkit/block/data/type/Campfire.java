/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.Waterlogged;

public interface Campfire
extends Directional,
Lightable,
Waterlogged {
    public boolean isSignalFire();

    public void setSignalFire(boolean var1);
}

