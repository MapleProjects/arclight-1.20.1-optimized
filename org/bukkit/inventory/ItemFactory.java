/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemFactory {
    @Nullable
    public ItemMeta getItemMeta(@NotNull Material var1);

    public boolean isApplicable(@Nullable ItemMeta var1, @Nullable ItemStack var2) throws IllegalArgumentException;

    public boolean isApplicable(@Nullable ItemMeta var1, @Nullable Material var2) throws IllegalArgumentException;

    public boolean equals(@Nullable ItemMeta var1, @Nullable ItemMeta var2) throws IllegalArgumentException;

    @Nullable
    public ItemMeta asMetaFor(@NotNull ItemMeta var1, @NotNull ItemStack var2) throws IllegalArgumentException;

    @Nullable
    public ItemMeta asMetaFor(@NotNull ItemMeta var1, @NotNull Material var2) throws IllegalArgumentException;

    @NotNull
    public Color getDefaultLeatherColor();

    @NotNull
    public ItemStack createItemStack(@NotNull String var1) throws IllegalArgumentException;

    @Deprecated
    @NotNull
    public Material updateMaterial(@NotNull ItemMeta var1, @NotNull Material var2) throws IllegalArgumentException;

    @Nullable
    public Material getSpawnEgg(@NotNull EntityType var1);
}

