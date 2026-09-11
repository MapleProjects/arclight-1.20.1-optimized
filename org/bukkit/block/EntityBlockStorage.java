/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import java.util.List;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityBlockStorage<T extends Entity>
extends TileState {
    public boolean isFull();

    public int getEntityCount();

    public int getMaxEntities();

    public void setMaxEntities(int var1);

    @NotNull
    public List<T> releaseEntities();

    public void addEntity(@NotNull T var1);
}

