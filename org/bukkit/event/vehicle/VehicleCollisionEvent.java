/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleEvent;
import org.jetbrains.annotations.NotNull;

public abstract class VehicleCollisionEvent
extends VehicleEvent {
    public VehicleCollisionEvent(@NotNull Vehicle vehicle) {
        super(vehicle);
    }
}

