/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.jetbrains.annotations.NotNull;

public class ItemSpawnEvent
extends EntitySpawnEvent {
    @Deprecated
    public ItemSpawnEvent(@NotNull Item spawnee, Location loc) {
        this(spawnee);
    }

    public ItemSpawnEvent(@NotNull Item spawnee) {
        super(spawnee);
    }

    @Override
    @NotNull
    public Item getEntity() {
        return (Item)this.entity;
    }
}

