/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

public interface ChiseledBookshelfInventory
extends Inventory {
    @Override
    @Nullable
    public ChiseledBookshelf getHolder();
}

