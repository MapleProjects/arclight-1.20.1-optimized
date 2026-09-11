/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Warden
extends Monster {
    public int getAnger();

    public int getAnger(@NotNull Entity var1);

    public void increaseAnger(@NotNull Entity var1, int var2);

    public void setAnger(@NotNull Entity var1, int var2);

    public void clearAnger(@NotNull Entity var1);

    @Nullable
    public LivingEntity getEntityAngryAt();

    public void setDisturbanceLocation(@NotNull Location var1);

    @NotNull
    public AngerLevel getAngerLevel();

    public static enum AngerLevel {
        CALM,
        AGITATED,
        ANGRY;

    }
}

