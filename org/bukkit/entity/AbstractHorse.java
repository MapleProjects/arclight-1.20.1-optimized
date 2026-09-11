/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface AbstractHorse
extends Vehicle,
InventoryHolder,
Tameable {
    @Deprecated
    @NotNull
    public Horse.Variant getVariant();

    @Deprecated
    @Contract(value="_ -> fail")
    public void setVariant(Horse.Variant var1);

    public int getDomestication();

    public void setDomestication(int var1);

    public int getMaxDomestication();

    public void setMaxDomestication(int var1);

    public double getJumpStrength();

    public void setJumpStrength(double var1);

    public boolean isEatingHaystack();

    public void setEatingHaystack(boolean var1);

    @Override
    @NotNull
    public AbstractHorseInventory getInventory();
}

