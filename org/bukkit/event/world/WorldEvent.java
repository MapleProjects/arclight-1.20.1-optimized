/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class WorldEvent
extends Event {
    private final World world;

    public WorldEvent(@NotNull World world) {
        this(world, false);
    }

    public WorldEvent(@NotNull World world, boolean isAsync) {
        super(isAsync);
        this.world = world;
    }

    @NotNull
    public World getWorld() {
        return this.world;
    }
}

