/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  com.google.common.collect.Iterables
 *  net.minecraft.world.scores.Objective
 *  net.minecraft.world.scores.PlayerTeam
 *  net.minecraft.world.scores.Scoreboard
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftCriteria;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftObjective;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScore;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboardTranslations;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftTeam;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;

public final class CraftScoreboard
implements org.bukkit.scoreboard.Scoreboard {
    final Scoreboard board;

    CraftScoreboard(Scoreboard board) {
        this.board = board;
    }

    @Override
    public CraftObjective registerNewObjective(String name, String criteria) {
        return this.registerNewObjective(name, criteria, name);
    }

    @Override
    public CraftObjective registerNewObjective(String name, String criteria, String displayName) {
        return this.registerNewObjective(name, CraftCriteria.getFromBukkit(criteria), displayName, RenderType.INTEGER);
    }

    @Override
    public CraftObjective registerNewObjective(String name, String criteria, String displayName, RenderType renderType) {
        return this.registerNewObjective(name, CraftCriteria.getFromBukkit(criteria), displayName, renderType);
    }

    @Override
    public CraftObjective registerNewObjective(String name, Criteria criteria, String displayName) {
        return this.registerNewObjective(name, criteria, displayName, RenderType.INTEGER);
    }

    @Override
    public CraftObjective registerNewObjective(String name, Criteria criteria, String displayName, RenderType renderType) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Objective name cannot be null");
        Preconditions.checkArgument((criteria != null ? 1 : 0) != 0, (Object)"Criteria cannot be null");
        Preconditions.checkArgument((displayName != null ? 1 : 0) != 0, (Object)"Display name cannot be null");
        Preconditions.checkArgument((renderType != null ? 1 : 0) != 0, (Object)"RenderType cannot be null");
        Preconditions.checkArgument((name.length() <= Short.MAX_VALUE ? 1 : 0) != 0, (String)"The name '%s' is longer than the limit of 32767 characters (%s)", (Object)name, (int)name.length());
        Preconditions.checkArgument((this.board.m_83477_(name) == null ? 1 : 0) != 0, (String)"An objective of name '%s' already exists", (Object)name);
        Objective objective = this.board.m_83436_(name, ((CraftCriteria)criteria).criteria, CraftChatMessage.fromStringOrNull(displayName), CraftScoreboardTranslations.fromBukkitRender(renderType));
        return new CraftObjective(this, objective);
    }

    @Override
    public org.bukkit.scoreboard.Objective getObjective(String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Objective name cannot be null");
        Objective nms = this.board.m_83477_(name);
        return nms == null ? null : new CraftObjective(this, nms);
    }

    public ImmutableSet<org.bukkit.scoreboard.Objective> getObjectivesByCriteria(String criteria) {
        Preconditions.checkArgument((criteria != null ? 1 : 0) != 0, (Object)"Criteria name cannot be null");
        ImmutableSet.Builder objectives = ImmutableSet.builder();
        for (Objective netObjective : this.board.m_83466_()) {
            CraftObjective objective = new CraftObjective(this, netObjective);
            if (!objective.getCriteria().equals(criteria)) continue;
            objectives.add((Object)objective);
        }
        return objectives.build();
    }

    public ImmutableSet<org.bukkit.scoreboard.Objective> getObjectivesByCriteria(Criteria criteria) {
        Preconditions.checkArgument((criteria != null ? 1 : 0) != 0, (Object)"Criteria cannot be null");
        ImmutableSet.Builder objectives = ImmutableSet.builder();
        for (Objective netObjective : this.board.m_83466_()) {
            CraftObjective objective = new CraftObjective(this, netObjective);
            if (!objective.getTrackedCriteria().equals(criteria)) continue;
            objectives.add((Object)objective);
        }
        return objectives.build();
    }

    public ImmutableSet<org.bukkit.scoreboard.Objective> getObjectives() {
        return ImmutableSet.copyOf((Iterable)Iterables.transform((Iterable)this.board.m_83466_(), input -> new CraftObjective(this, (Objective)input)));
    }

    @Override
    public org.bukkit.scoreboard.Objective getObjective(DisplaySlot slot) {
        Preconditions.checkArgument((slot != null ? 1 : 0) != 0, (Object)"Display slot cannot be null");
        Objective objective = this.board.m_83416_(CraftScoreboardTranslations.fromBukkitSlot(slot));
        if (objective == null) {
            return null;
        }
        return new CraftObjective(this, objective);
    }

    public ImmutableSet<Score> getScores(OfflinePlayer player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"OfflinePlayer cannot be null");
        return this.getScores(player.getName());
    }

    public ImmutableSet<Score> getScores(String entry) {
        Preconditions.checkArgument((entry != null ? 1 : 0) != 0, (Object)"Entry cannot be null");
        ImmutableSet.Builder scores = ImmutableSet.builder();
        for (Objective objective : this.board.m_83466_()) {
            scores.add((Object)new CraftScore(new CraftObjective(this, objective), entry));
        }
        return scores.build();
    }

    @Override
    public void resetScores(OfflinePlayer player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"OfflinePlayer cannot be null");
        this.resetScores(player.getName());
    }

    @Override
    public void resetScores(String entry) {
        Preconditions.checkArgument((entry != null ? 1 : 0) != 0, (Object)"Entry cannot be null");
        for (Objective objective : this.board.m_83466_()) {
            this.board.m_83479_(entry, objective);
        }
    }

    @Override
    public Team getPlayerTeam(OfflinePlayer player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"OfflinePlayer cannot be null");
        PlayerTeam team = this.board.m_83500_(player.getName());
        return team == null ? null : new CraftTeam(this, team);
    }

    @Override
    public Team getEntryTeam(String entry) {
        Preconditions.checkArgument((entry != null ? 1 : 0) != 0, (Object)"Entry cannot be null");
        PlayerTeam team = this.board.m_83500_(entry);
        return team == null ? null : new CraftTeam(this, team);
    }

    @Override
    public Team getTeam(String teamName) {
        Preconditions.checkArgument((teamName != null ? 1 : 0) != 0, (Object)"Team name cannot be null");
        PlayerTeam team = this.board.m_83489_(teamName);
        return team == null ? null : new CraftTeam(this, team);
    }

    public ImmutableSet<Team> getTeams() {
        return ImmutableSet.copyOf((Iterable)Iterables.transform((Iterable)this.board.m_83491_(), input -> new CraftTeam(this, (PlayerTeam)input)));
    }

    @Override
    public Team registerNewTeam(String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Team name cannot be null");
        Preconditions.checkArgument((name.length() <= Short.MAX_VALUE ? 1 : 0) != 0, (String)"Team name '%s' is longer than the limit of 32767 characters (%s)", (Object)name, (int)name.length());
        Preconditions.checkArgument((this.board.m_83489_(name) == null ? 1 : 0) != 0, (String)"Team name '%s' is already in use", (Object)name);
        return new CraftTeam(this, this.board.m_83492_(name));
    }

    public ImmutableSet<OfflinePlayer> getPlayers() {
        ImmutableSet.Builder players = ImmutableSet.builder();
        for (Object playerName : this.board.m_83482_()) {
            players.add((Object)Bukkit.getOfflinePlayer(playerName.toString()));
        }
        return players.build();
    }

    public ImmutableSet<String> getEntries() {
        ImmutableSet.Builder entries = ImmutableSet.builder();
        for (Object entry : this.board.m_83482_()) {
            entries.add((Object)entry.toString());
        }
        return entries.build();
    }

    @Override
    public void clearSlot(DisplaySlot slot) {
        Preconditions.checkArgument((slot != null ? 1 : 0) != 0, (Object)"Slot cannot be null");
        this.board.m_7136_(CraftScoreboardTranslations.fromBukkitSlot(slot), null);
    }

    public Scoreboard getHandle() {
        return this.board;
    }
}

