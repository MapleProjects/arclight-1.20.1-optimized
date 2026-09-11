/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.raid;

import java.util.Collections;
import java.util.List;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Raider;
import org.bukkit.event.HandlerList;
import org.bukkit.event.raid.RaidEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RaidSpawnWaveEvent
extends RaidEvent {
    private static final HandlerList handlers = new HandlerList();
    private final List<Raider> raiders;
    private final Raider leader;

    public RaidSpawnWaveEvent(@NotNull Raid raid, @NotNull World world, @Nullable Raider leader, @NotNull List<Raider> raiders) {
        super(raid, world);
        this.raiders = raiders;
        this.leader = leader;
    }

    @Nullable
    public Raider getPatrolLeader() {
        return this.leader;
    }

    @NotNull
    public List<Raider> getRaiders() {
        return Collections.unmodifiableList(this.raiders);
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

