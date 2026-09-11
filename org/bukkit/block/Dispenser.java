/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.Nameable;
import org.bukkit.block.Container;
import org.bukkit.loot.Lootable;
import org.bukkit.projectiles.BlockProjectileSource;
import org.jetbrains.annotations.Nullable;

public interface Dispenser
extends Container,
Nameable,
Lootable {
    @Nullable
    public BlockProjectileSource getBlockProjectileSource();

    public boolean dispense();
}

