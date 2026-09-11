/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Objective {
    @NotNull
    public String getName();

    @NotNull
    public String getDisplayName();

    public void setDisplayName(@NotNull String var1);

    @Deprecated
    @NotNull
    public String getCriteria();

    @NotNull
    public Criteria getTrackedCriteria();

    public boolean isModifiable();

    @Nullable
    public Scoreboard getScoreboard();

    public void unregister();

    public void setDisplaySlot(@Nullable DisplaySlot var1);

    @Nullable
    public DisplaySlot getDisplaySlot();

    public void setRenderType(@NotNull RenderType var1);

    @NotNull
    public RenderType getRenderType();

    @Deprecated
    @NotNull
    public Score getScore(@NotNull OfflinePlayer var1);

    @NotNull
    public Score getScore(@NotNull String var1);
}

