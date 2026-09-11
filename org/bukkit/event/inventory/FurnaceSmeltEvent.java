/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FurnaceSmeltEvent
extends BlockCookEvent {
    public FurnaceSmeltEvent(@NotNull Block furnace, @NotNull ItemStack source, @NotNull ItemStack result) {
        super(furnace, source, result);
    }
}

