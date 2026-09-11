/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Recipe {
    @NotNull
    public ItemStack getResult();
}

