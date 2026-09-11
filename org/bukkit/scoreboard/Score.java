/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Score {
    @Deprecated
    @NotNull
    public OfflinePlayer getPlayer();

    @NotNull
    public String getEntry();

    @NotNull
    public Objective getObjective();

    public int getScore();

    public void setScore(int var1);

    public boolean isScoreSet();

    @Nullable
    public Scoreboard getScoreboard();
}

