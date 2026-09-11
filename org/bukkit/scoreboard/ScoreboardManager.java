/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

public interface ScoreboardManager {
    @NotNull
    public Scoreboard getMainScoreboard();

    @NotNull
    public Scoreboard getNewScoreboard();
}

