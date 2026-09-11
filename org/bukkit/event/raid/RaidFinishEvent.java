/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.raid;

import java.util.Collections;
import java.util.List;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.raid.RaidEvent;
import org.jetbrains.annotations.NotNull;

public class RaidFinishEvent
extends RaidEvent {
    private static final HandlerList handlers = new HandlerList();
    private final List<Player> winners;

    public RaidFinishEvent(@NotNull Raid raid, @NotNull World world, @NotNull List<Player> winners) {
        super(raid, world);
        this.winners = winners;
    }

    @NotNull
    public List<Player> getWinners() {
        return Collections.unmodifiableList(this.winners);
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

