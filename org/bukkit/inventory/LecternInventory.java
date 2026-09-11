/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.block.Lectern;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

public interface LecternInventory
extends Inventory {
    @Override
    @Nullable
    public Lectern getHolder();
}

