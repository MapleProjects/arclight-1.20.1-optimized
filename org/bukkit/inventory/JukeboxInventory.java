/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.block.Jukebox;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface JukeboxInventory
extends Inventory {
    public void setRecord(@Nullable ItemStack var1);

    @Nullable
    public ItemStack getRecord();

    @Override
    @Nullable
    public Jukebox getHolder();
}

