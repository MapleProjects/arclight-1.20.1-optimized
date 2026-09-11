/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public interface Repairable
extends ItemMeta {
    public boolean hasRepairCost();

    public int getRepairCost();

    public void setRepairCost(int var1);

    @Override
    @NotNull
    public Repairable clone();
}

