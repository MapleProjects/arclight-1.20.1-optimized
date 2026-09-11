/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import java.util.Set;
import org.bukkit.Material;
import org.bukkit.entity.PiglinAbstract;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public interface Piglin
extends PiglinAbstract,
InventoryHolder {
    public boolean isAbleToHunt();

    public void setIsAbleToHunt(boolean var1);

    public boolean addBarterMaterial(@NotNull Material var1);

    public boolean removeBarterMaterial(@NotNull Material var1);

    public boolean addMaterialOfInterest(@NotNull Material var1);

    public boolean removeMaterialOfInterest(@NotNull Material var1);

    @NotNull
    public Set<Material> getInterestList();

    @NotNull
    public Set<Material> getBarterList();
}

