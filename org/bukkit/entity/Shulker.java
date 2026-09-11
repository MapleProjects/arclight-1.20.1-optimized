/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Enemy;
import org.bukkit.entity.Golem;
import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

public interface Shulker
extends Golem,
Colorable,
Enemy {
    public float getPeek();

    public void setPeek(float var1);

    @NotNull
    public BlockFace getAttachedFace();

    public void setAttachedFace(@NotNull BlockFace var1);
}

