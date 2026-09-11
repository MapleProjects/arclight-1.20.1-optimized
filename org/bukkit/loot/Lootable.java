/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.loot;

import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.Nullable;

public interface Lootable {
    public void setLootTable(@Nullable LootTable var1);

    @Nullable
    public LootTable getLootTable();

    public void setSeed(long var1);

    public long getSeed();
}

