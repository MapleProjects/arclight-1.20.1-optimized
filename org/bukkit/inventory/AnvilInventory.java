/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

public interface AnvilInventory
extends Inventory {
    @Nullable
    public String getRenameText();

    public int getRepairCostAmount();

    public void setRepairCostAmount(int var1);

    public int getRepairCost();

    public void setRepairCost(int var1);

    public int getMaximumRepairCost();

    public void setMaximumRepairCost(int var1);
}

