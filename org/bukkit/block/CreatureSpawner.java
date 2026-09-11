/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Nullable;

public interface CreatureSpawner
extends TileState {
    @Nullable
    public EntityType getSpawnedType();

    public void setSpawnedType(@Nullable EntityType var1);

    @Deprecated
    public void setCreatureTypeByName(@Nullable String var1);

    @Deprecated
    @Nullable
    public String getCreatureTypeName();

    public int getDelay();

    public void setDelay(int var1);

    public int getMinSpawnDelay();

    public void setMinSpawnDelay(int var1);

    public int getMaxSpawnDelay();

    public void setMaxSpawnDelay(int var1);

    public int getSpawnCount();

    public void setSpawnCount(int var1);

    public int getMaxNearbyEntities();

    public void setMaxNearbyEntities(int var1);

    public int getRequiredPlayerRange();

    public void setRequiredPlayerRange(int var1);

    public int getSpawnRange();

    public void setSpawnRange(int var1);
}

