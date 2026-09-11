/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Inventory
extends Iterable<ItemStack> {
    public int getSize();

    public int getMaxStackSize();

    public void setMaxStackSize(int var1);

    @Nullable
    public ItemStack getItem(int var1);

    public void setItem(int var1, @Nullable ItemStack var2);

    @NotNull
    public HashMap<Integer, ItemStack> addItem(ItemStack ... var1) throws IllegalArgumentException;

    @NotNull
    public HashMap<Integer, ItemStack> removeItem(ItemStack ... var1) throws IllegalArgumentException;

    @NotNull
    public ItemStack[] getContents();

    public void setContents(@NotNull ItemStack[] var1) throws IllegalArgumentException;

    @NotNull
    public ItemStack[] getStorageContents();

    public void setStorageContents(@NotNull ItemStack[] var1) throws IllegalArgumentException;

    public boolean contains(@NotNull Material var1) throws IllegalArgumentException;

    @Contract(value="null -> false")
    public boolean contains(@Nullable ItemStack var1);

    public boolean contains(@NotNull Material var1, int var2) throws IllegalArgumentException;

    @Contract(value="null, _ -> false")
    public boolean contains(@Nullable ItemStack var1, int var2);

    @Contract(value="null, _ -> false")
    public boolean containsAtLeast(@Nullable ItemStack var1, int var2);

    @NotNull
    public HashMap<Integer, ? extends ItemStack> all(@NotNull Material var1) throws IllegalArgumentException;

    @NotNull
    public HashMap<Integer, ? extends ItemStack> all(@Nullable ItemStack var1);

    public int first(@NotNull Material var1) throws IllegalArgumentException;

    public int first(@NotNull ItemStack var1);

    public int firstEmpty();

    public boolean isEmpty();

    public void remove(@NotNull Material var1) throws IllegalArgumentException;

    public void remove(@NotNull ItemStack var1);

    public void clear(int var1);

    public void clear();

    @NotNull
    public List<HumanEntity> getViewers();

    @NotNull
    public InventoryType getType();

    @Nullable
    public InventoryHolder getHolder();

    @Override
    @NotNull
    public ListIterator<ItemStack> iterator();

    @NotNull
    public ListIterator<ItemStack> iterator(int var1);

    @Nullable
    public Location getLocation();
}

