/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.enchantments;

import com.google.common.base.Preconditions;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public class EnchantmentOffer {
    private Enchantment enchantment;
    private int enchantmentLevel;
    private int cost;

    public EnchantmentOffer(@NotNull Enchantment enchantment, int enchantmentLevel, int cost) {
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
        this.cost = cost;
    }

    @NotNull
    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    public void setEnchantment(@NotNull Enchantment enchantment) {
        Preconditions.checkArgument((enchantment != null ? 1 : 0) != 0, (Object)"The enchantment may not be null!");
        this.enchantment = enchantment;
    }

    public int getEnchantmentLevel() {
        return this.enchantmentLevel;
    }

    public void setEnchantmentLevel(int enchantmentLevel) {
        Preconditions.checkArgument((enchantmentLevel > 0 ? 1 : 0) != 0, (Object)"The enchantment level must be greater than 0!");
        this.enchantmentLevel = enchantmentLevel;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        Preconditions.checkArgument((cost > 0 ? 1 : 0) != 0, (Object)"The cost must be greater than 0!");
        this.cost = cost;
    }
}

