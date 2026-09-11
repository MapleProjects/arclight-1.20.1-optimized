/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Sniffer
extends Animals {
    @NotNull
    public Collection<Location> getExploredLocations();

    public void removeExploredLocation(@NotNull Location var1);

    public void addExploredLocation(@NotNull Location var1);

    @NotNull
    public State getState();

    public void setState(@NotNull State var1);

    @Nullable
    public Location findPossibleDigLocation();

    public boolean canDig();

    public static enum State {
        IDLING,
        FEELING_HAPPY,
        SCENTING,
        SNIFFING,
        SEARCHING,
        DIGGING,
        RISING;

    }
}

