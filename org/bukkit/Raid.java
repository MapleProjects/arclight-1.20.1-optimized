/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Raider;
import org.jetbrains.annotations.NotNull;

public interface Raid {
    public boolean isStarted();

    public long getActiveTicks();

    public int getBadOmenLevel();

    public void setBadOmenLevel(int var1);

    @NotNull
    public Location getLocation();

    @NotNull
    public RaidStatus getStatus();

    public int getSpawnedGroups();

    public int getTotalGroups();

    public int getTotalWaves();

    public float getTotalHealth();

    @NotNull
    public Set<UUID> getHeroes();

    @NotNull
    public List<Raider> getRaiders();

    public static enum RaidStatus {
        ONGOING,
        VICTORY,
        LOSS,
        STOPPED;

    }
}

