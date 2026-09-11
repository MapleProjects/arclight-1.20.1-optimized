/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.DyeColor;
import org.bukkit.block.Container;
import org.bukkit.block.Lidded;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.Nullable;

public interface ShulkerBox
extends Container,
Lootable,
Lidded {
    @Nullable
    public DyeColor getColor();
}

