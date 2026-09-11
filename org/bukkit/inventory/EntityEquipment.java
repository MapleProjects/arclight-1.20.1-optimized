/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityEquipment {
    public void setItem(@NotNull EquipmentSlot var1, @Nullable ItemStack var2);

    public void setItem(@NotNull EquipmentSlot var1, @Nullable ItemStack var2, boolean var3);

    @NotNull
    public ItemStack getItem(@NotNull EquipmentSlot var1);

    @NotNull
    public ItemStack getItemInMainHand();

    public void setItemInMainHand(@Nullable ItemStack var1);

    public void setItemInMainHand(@Nullable ItemStack var1, boolean var2);

    @NotNull
    public ItemStack getItemInOffHand();

    public void setItemInOffHand(@Nullable ItemStack var1);

    public void setItemInOffHand(@Nullable ItemStack var1, boolean var2);

    @Deprecated
    @NotNull
    public ItemStack getItemInHand();

    @Deprecated
    public void setItemInHand(@Nullable ItemStack var1);

    @Nullable
    public ItemStack getHelmet();

    public void setHelmet(@Nullable ItemStack var1);

    public void setHelmet(@Nullable ItemStack var1, boolean var2);

    @Nullable
    public ItemStack getChestplate();

    public void setChestplate(@Nullable ItemStack var1);

    public void setChestplate(@Nullable ItemStack var1, boolean var2);

    @Nullable
    public ItemStack getLeggings();

    public void setLeggings(@Nullable ItemStack var1);

    public void setLeggings(@Nullable ItemStack var1, boolean var2);

    @Nullable
    public ItemStack getBoots();

    public void setBoots(@Nullable ItemStack var1);

    public void setBoots(@Nullable ItemStack var1, boolean var2);

    @NotNull
    public ItemStack[] getArmorContents();

    public void setArmorContents(@NotNull ItemStack[] var1);

    public void clear();

    @Deprecated
    public float getItemInHandDropChance();

    @Deprecated
    public void setItemInHandDropChance(float var1);

    public float getItemInMainHandDropChance();

    public void setItemInMainHandDropChance(float var1);

    public float getItemInOffHandDropChance();

    public void setItemInOffHandDropChance(float var1);

    public float getHelmetDropChance();

    public void setHelmetDropChance(float var1);

    public float getChestplateDropChance();

    public void setChestplateDropChance(float var1);

    public float getLeggingsDropChance();

    public void setLeggingsDropChance(float var1);

    public float getBootsDropChance();

    public void setBootsDropChance(float var1);

    @Nullable
    public Entity getHolder();
}

