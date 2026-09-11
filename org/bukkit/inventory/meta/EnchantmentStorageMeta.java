/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface EnchantmentStorageMeta
extends ItemMeta {
    public boolean hasStoredEnchants();

    public boolean hasStoredEnchant(@NotNull Enchantment var1);

    public int getStoredEnchantLevel(@NotNull Enchantment var1);

    @NotNull
    public Map<Enchantment, Integer> getStoredEnchants();

    public boolean addStoredEnchant(@NotNull Enchantment var1, int var2, boolean var3);

    public boolean removeStoredEnchant(@NotNull Enchantment var1) throws IllegalArgumentException;

    public boolean hasConflictingStoredEnchant(@NotNull Enchantment var1);

    @Override
    @NotNull
    public EnchantmentStorageMeta clone();
}

