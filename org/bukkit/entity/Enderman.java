/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Enderman
extends Monster {
    @NotNull
    public MaterialData getCarriedMaterial();

    public void setCarriedMaterial(@NotNull MaterialData var1);

    @Nullable
    public BlockData getCarriedBlock();

    public void setCarriedBlock(@Nullable BlockData var1);

    @ApiStatus.Experimental
    public boolean teleport();

    @ApiStatus.Experimental
    public boolean teleportTowards(@NotNull Entity var1);
}

