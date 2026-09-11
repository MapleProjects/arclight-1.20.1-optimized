/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.material;

import org.bukkit.block.BlockFace;
import org.bukkit.material.Directional;
import org.jetbrains.annotations.NotNull;

public interface Attachable
extends Directional {
    @NotNull
    public BlockFace getAttachedFace();
}

