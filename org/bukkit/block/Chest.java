/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.block.Container;
import org.bukkit.block.Lidded;
import org.bukkit.inventory.Inventory;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.NotNull;

public interface Chest
extends Container,
Lootable,
Lidded {
    @NotNull
    public Inventory getBlockInventory();
}

