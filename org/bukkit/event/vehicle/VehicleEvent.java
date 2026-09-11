/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class VehicleEvent
extends Event {
    protected Vehicle vehicle;

    public VehicleEvent(@NotNull Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @NotNull
    public final Vehicle getVehicle() {
        return this.vehicle;
    }
}

