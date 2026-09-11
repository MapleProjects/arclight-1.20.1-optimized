/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.projectiles;

import org.bukkit.block.Block;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

public interface BlockProjectileSource
extends ProjectileSource {
    @NotNull
    public Block getBlock();
}

