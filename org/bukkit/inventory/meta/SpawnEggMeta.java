/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface SpawnEggMeta
extends ItemMeta {
    @Deprecated
    @Contract(value="-> fail")
    public EntityType getSpawnedType();

    @Deprecated
    @Contract(value="_ -> fail")
    public void setSpawnedType(EntityType var1);

    @Override
    @NotNull
    public SpawnEggMeta clone();
}

