/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.Container
 *  net.minecraft.world.inventory.AnvilMenu
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AnvilMenu;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftResultInventory;
import org.bukkit.inventory.AnvilInventory;

public class CraftInventoryAnvil
extends CraftResultInventory
implements AnvilInventory {
    private final Location location;
    private final AnvilMenu container;

    public CraftInventoryAnvil(Location location, Container inventory, Container resultInventory, AnvilMenu container) {
        super(inventory, resultInventory);
        this.location = location;
        this.container = container;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public String getRenameText() {
        return this.container.f_39001_;
    }

    @Override
    public int getRepairCostAmount() {
        return this.container.f_39000_;
    }

    @Override
    public void setRepairCostAmount(int amount) {
        this.container.f_39000_ = amount;
    }

    @Override
    public int getRepairCost() {
        return this.container.f_39002_.m_6501_();
    }

    @Override
    public void setRepairCost(int i) {
        this.container.f_39002_.m_6422_(i);
    }

    @Override
    public int getMaximumRepairCost() {
        return this.container.maximumRepairCost;
    }

    @Override
    public void setMaximumRepairCost(int levels) {
        Preconditions.checkArgument((levels >= 0 ? 1 : 0) != 0, (Object)"Maximum repair cost must be positive (or 0)");
        this.container.maximumRepairCost = levels;
    }
}

