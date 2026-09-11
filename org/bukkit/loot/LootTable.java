/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.loot;

import java.util.Collection;
import java.util.Random;
import org.bukkit.Keyed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LootTable
extends Keyed {
    @NotNull
    public Collection<ItemStack> populateLoot(@Nullable Random var1, @NotNull LootContext var2);

    public void fillInventory(@NotNull Inventory var1, @Nullable Random var2, @NotNull LootContext var3);
}

