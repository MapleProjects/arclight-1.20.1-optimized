/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.raid;

import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;

public abstract class RaidEvent
extends WorldEvent {
    private final Raid raid;

    protected RaidEvent(@NotNull Raid raid, @NotNull World world) {
        super(world);
        this.raid = raid;
    }

    @NotNull
    public Raid getRaid() {
        return this.raid;
    }
}

