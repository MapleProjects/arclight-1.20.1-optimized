/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.profile;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PlayerProfile
extends Cloneable,
ConfigurationSerializable {
    @Nullable
    public UUID getUniqueId();

    @Nullable
    public String getName();

    @NotNull
    public PlayerTextures getTextures();

    public void setTextures(@Nullable PlayerTextures var1);

    public boolean isComplete();

    @NotNull
    public CompletableFuture<PlayerProfile> update();

    @NotNull
    public PlayerProfile clone();
}

