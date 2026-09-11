/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.weather;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class WeatherEvent
extends Event {
    protected World world;

    public WeatherEvent(@NotNull World where) {
        this.world = where;
    }

    @NotNull
    public final World getWorld() {
        return this.world;
    }
}

