/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.block.BlockState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

public interface TileState
extends BlockState,
PersistentDataHolder {
    @Override
    @NotNull
    public PersistentDataContainer getPersistentDataContainer();
}

