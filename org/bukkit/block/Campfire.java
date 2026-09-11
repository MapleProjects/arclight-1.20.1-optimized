/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface Campfire
extends TileState {
    public int getSize();

    @Nullable
    public ItemStack getItem(int var1);

    public void setItem(int var1, @Nullable ItemStack var2);

    public int getCookTime(int var1);

    public void setCookTime(int var1, int var2);

    public int getCookTimeTotal(int var1);

    public void setCookTimeTotal(int var1, int var2);
}

