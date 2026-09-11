/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.material.Attachable;
import org.jetbrains.annotations.NotNull;

public interface Hanging
extends Entity,
Attachable {
    public boolean setFacingDirection(@NotNull BlockFace var1, boolean var2);
}

