/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.jetbrains.annotations.NotNull;

public class SpawnerSpawnEvent
extends EntitySpawnEvent {
    private final CreatureSpawner spawner;

    public SpawnerSpawnEvent(@NotNull Entity spawnee, @NotNull CreatureSpawner spawner) {
        super(spawnee);
        this.spawner = spawner;
    }

    @NotNull
    public CreatureSpawner getSpawner() {
        return this.spawner;
    }
}

