/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Interaction
extends Entity {
    public float getInteractionWidth();

    public void setInteractionWidth(float var1);

    public float getInteractionHeight();

    public void setInteractionHeight(float var1);

    public boolean isResponsive();

    public void setResponsive(boolean var1);

    @Nullable
    public PreviousInteraction getLastAttack();

    @Nullable
    public PreviousInteraction getLastInteraction();

    public static interface PreviousInteraction {
        @NotNull
        public OfflinePlayer getPlayer();

        public long getTimestamp();
    }
}

