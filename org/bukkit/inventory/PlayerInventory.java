/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PlayerInventory
extends Inventory {
    @NotNull
    public ItemStack[] getArmorContents();

    @NotNull
    public ItemStack[] getExtraContents();

    @Nullable
    public ItemStack getHelmet();

    @Nullable
    public ItemStack getChestplate();

    @Nullable
    public ItemStack getLeggings();

    @Nullable
    public ItemStack getBoots();

    @Override
    public void setItem(int var1, @Nullable ItemStack var2);

    public void setItem(@NotNull EquipmentSlot var1, @Nullable ItemStack var2);

    @Nullable
    public ItemStack getItem(@NotNull EquipmentSlot var1);

    public void setArmorContents(@Nullable ItemStack[] var1);

    public void setExtraContents(@Nullable ItemStack[] var1);

    public void setHelmet(@Nullable ItemStack var1);

    public void setChestplate(@Nullable ItemStack var1);

    public void setLeggings(@Nullable ItemStack var1);

    public void setBoots(@Nullable ItemStack var1);

    @NotNull
    public ItemStack getItemInMainHand();

    public void setItemInMainHand(@Nullable ItemStack var1);

    @NotNull
    public ItemStack getItemInOffHand();

    public void setItemInOffHand(@Nullable ItemStack var1);

    @Deprecated
    @NotNull
    public ItemStack getItemInHand();

    @Deprecated
    public void setItemInHand(@Nullable ItemStack var1);

    public int getHeldItemSlot();

    public void setHeldItemSlot(int var1);

    @Override
    @Nullable
    public HumanEntity getHolder();
}

