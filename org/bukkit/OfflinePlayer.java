/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import org.bukkit.BanEntry;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface OfflinePlayer
extends ServerOperator,
AnimalTamer,
ConfigurationSerializable {
    public boolean isOnline();

    @Override
    @Nullable
    public String getName();

    @Override
    @NotNull
    public UUID getUniqueId();

    @NotNull
    public PlayerProfile getPlayerProfile();

    public boolean isBanned();

    @Nullable
    public BanEntry<PlayerProfile> ban(@Nullable String var1, @Nullable Date var2, @Nullable String var3);

    @Nullable
    public BanEntry<PlayerProfile> ban(@Nullable String var1, @Nullable Instant var2, @Nullable String var3);

    @Nullable
    public BanEntry<PlayerProfile> ban(@Nullable String var1, @Nullable Duration var2, @Nullable String var3);

    public boolean isWhitelisted();

    public void setWhitelisted(boolean var1);

    @Nullable
    public Player getPlayer();

    public long getFirstPlayed();

    public long getLastPlayed();

    public boolean hasPlayedBefore();

    @Nullable
    public Location getBedSpawnLocation();

    public void incrementStatistic(@NotNull Statistic var1) throws IllegalArgumentException;

    public void decrementStatistic(@NotNull Statistic var1) throws IllegalArgumentException;

    public void incrementStatistic(@NotNull Statistic var1, int var2) throws IllegalArgumentException;

    public void decrementStatistic(@NotNull Statistic var1, int var2) throws IllegalArgumentException;

    public void setStatistic(@NotNull Statistic var1, int var2) throws IllegalArgumentException;

    public int getStatistic(@NotNull Statistic var1) throws IllegalArgumentException;

    public void incrementStatistic(@NotNull Statistic var1, @NotNull Material var2) throws IllegalArgumentException;

    public void decrementStatistic(@NotNull Statistic var1, @NotNull Material var2) throws IllegalArgumentException;

    public int getStatistic(@NotNull Statistic var1, @NotNull Material var2) throws IllegalArgumentException;

    public void incrementStatistic(@NotNull Statistic var1, @NotNull Material var2, int var3) throws IllegalArgumentException;

    public void decrementStatistic(@NotNull Statistic var1, @NotNull Material var2, int var3) throws IllegalArgumentException;

    public void setStatistic(@NotNull Statistic var1, @NotNull Material var2, int var3) throws IllegalArgumentException;

    public void incrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2) throws IllegalArgumentException;

    public void decrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2) throws IllegalArgumentException;

    public int getStatistic(@NotNull Statistic var1, @NotNull EntityType var2) throws IllegalArgumentException;

    public void incrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2, int var3) throws IllegalArgumentException;

    public void decrementStatistic(@NotNull Statistic var1, @NotNull EntityType var2, int var3);

    public void setStatistic(@NotNull Statistic var1, @NotNull EntityType var2, int var3);

    @Nullable
    public Location getLastDeathLocation();
}

