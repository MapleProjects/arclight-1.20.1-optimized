/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnchantmentWrapper
extends Enchantment {
    public EnchantmentWrapper(@NotNull String name) {
        super(NamespacedKey.minecraft(name));
    }

    @NotNull
    public Enchantment getEnchantment() {
        return Enchantment.getByKey(this.getKey());
    }

    @Override
    public int getMaxLevel() {
        return this.getEnchantment().getMaxLevel();
    }

    @Override
    public int getStartLevel() {
        return this.getEnchantment().getStartLevel();
    }

    @Override
    @NotNull
    public EnchantmentTarget getItemTarget() {
        return this.getEnchantment().getItemTarget();
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return this.getEnchantment().canEnchantItem(item);
    }

    @Override
    @NotNull
    public String getName() {
        return this.getEnchantment().getName();
    }

    @Override
    public boolean isTreasure() {
        return this.getEnchantment().isTreasure();
    }

    @Override
    public boolean isCursed() {
        return this.getEnchantment().isCursed();
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return this.getEnchantment().conflictsWith(other);
    }
}

