/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Breedable;
import org.bukkit.entity.NPC;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.Merchant;
import org.jetbrains.annotations.NotNull;

public interface AbstractVillager
extends Breedable,
NPC,
InventoryHolder,
Merchant {
    @Override
    @NotNull
    public Inventory getInventory();
}

