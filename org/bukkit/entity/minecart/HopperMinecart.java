/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity.minecart;

import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.loot.Lootable;

public interface HopperMinecart
extends Minecart,
InventoryHolder,
Lootable {
    public boolean isEnabled();

    public void setEnabled(boolean var1);
}

