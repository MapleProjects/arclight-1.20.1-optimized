/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Breedable;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Animals
extends Breedable {
    @Nullable
    public UUID getBreedCause();

    public void setBreedCause(@Nullable UUID var1);

    public boolean isLoveMode();

    public int getLoveModeTicks();

    public void setLoveModeTicks(int var1);

    public boolean isBreedItem(@NotNull ItemStack var1);

    public boolean isBreedItem(@NotNull Material var1);
}

