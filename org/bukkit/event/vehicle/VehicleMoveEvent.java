/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.vehicle;

import org.bukkit.Location;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.HandlerList;
import org.bukkit.event.vehicle.VehicleEvent;
import org.jetbrains.annotations.NotNull;

public class VehicleMoveEvent
extends VehicleEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Location from;
    private final Location to;

    public VehicleMoveEvent(@NotNull Vehicle vehicle, @NotNull Location from, @NotNull Location to) {
        super(vehicle);
        this.from = from;
        this.to = to;
    }

    @NotNull
    public Location getFrom() {
        return this.from;
    }

    @NotNull
    public Location getTo() {
        return this.to;
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

