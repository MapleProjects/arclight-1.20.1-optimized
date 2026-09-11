/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.scores.Score
 *  net.minecraft.world.scores.Scoreboard
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import java.util.Map;
import net.minecraft.world.scores.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftObjective;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboard;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

final class CraftScore
implements Score {
    private final String entry;
    private final CraftObjective objective;

    CraftScore(CraftObjective objective, String entry) {
        this.objective = objective;
        this.entry = entry;
    }

    @Override
    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer(this.entry);
    }

    @Override
    public String getEntry() {
        return this.entry;
    }

    @Override
    public Objective getObjective() {
        return this.objective;
    }

    @Override
    public int getScore() {
        Map scores;
        net.minecraft.world.scores.Score score;
        Scoreboard board = this.objective.checkState().board;
        if (board.m_83482_().contains(this.entry) && (score = (net.minecraft.world.scores.Score)(scores = board.m_83483_(this.entry)).get(this.objective.getHandle())) != null) {
            return score.m_83400_();
        }
        return 0;
    }

    @Override
    public void setScore(int score) {
        this.objective.checkState().board.m_83471_(this.entry, this.objective.getHandle()).m_83402_(score);
    }

    @Override
    public boolean isScoreSet() {
        Scoreboard board = this.objective.checkState().board;
        return board.m_83482_().contains(this.entry) && board.m_83483_(this.entry).containsKey(this.objective.getHandle());
    }

    @Override
    public CraftScoreboard getScoreboard() {
        return this.objective.getScoreboard();
    }
}

