/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.scoreboard;

import java.util.Set;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Scoreboard {
    @Deprecated
    @NotNull
    public Objective registerNewObjective(@NotNull String var1, @NotNull String var2);

    @NotNull
    public Objective registerNewObjective(@NotNull String var1, @NotNull String var2, @NotNull String var3);

    @NotNull
    public Objective registerNewObjective(@NotNull String var1, @NotNull String var2, @NotNull String var3, @NotNull RenderType var4);

    @NotNull
    public Objective registerNewObjective(@NotNull String var1, @NotNull Criteria var2, @NotNull String var3);

    @NotNull
    public Objective registerNewObjective(@NotNull String var1, @NotNull Criteria var2, @NotNull String var3, @NotNull RenderType var4);

    @Nullable
    public Objective getObjective(@NotNull String var1);

    @Deprecated
    @NotNull
    public Set<Objective> getObjectivesByCriteria(@NotNull String var1);

    @NotNull
    public Set<Objective> getObjectivesByCriteria(@NotNull Criteria var1);

    @NotNull
    public Set<Objective> getObjectives();

    @Nullable
    public Objective getObjective(@NotNull DisplaySlot var1);

    @Deprecated
    @NotNull
    public Set<Score> getScores(@NotNull OfflinePlayer var1);

    @NotNull
    public Set<Score> getScores(@NotNull String var1);

    @Deprecated
    public void resetScores(@NotNull OfflinePlayer var1);

    public void resetScores(@NotNull String var1);

    @Deprecated
    @Nullable
    public Team getPlayerTeam(@NotNull OfflinePlayer var1);

    @Nullable
    public Team getEntryTeam(@NotNull String var1);

    @Nullable
    public Team getTeam(@NotNull String var1);

    @NotNull
    public Set<Team> getTeams();

    @NotNull
    public Team registerNewTeam(@NotNull String var1);

    @Deprecated
    @NotNull
    public Set<OfflinePlayer> getPlayers();

    @NotNull
    public Set<String> getEntries();

    public void clearSlot(@NotNull DisplaySlot var1);
}

