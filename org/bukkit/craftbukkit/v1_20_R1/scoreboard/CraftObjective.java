/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.scores.Objective
 *  net.minecraft.world.scores.Scoreboard
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import com.google.common.base.Preconditions;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftCriteria;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScore;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboard;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboardComponent;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboardTranslations;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;

final class CraftObjective
extends CraftScoreboardComponent
implements org.bukkit.scoreboard.Objective {
    private final Objective objective;
    private final CraftCriteria criteria;

    CraftObjective(CraftScoreboard scoreboard, Objective objective) {
        super(scoreboard);
        this.objective = objective;
        this.criteria = CraftCriteria.getFromNMS(objective);
    }

    Objective getHandle() {
        return this.objective;
    }

    @Override
    public String getName() {
        this.checkState();
        return this.objective.m_83320_();
    }

    @Override
    public String getDisplayName() {
        this.checkState();
        return CraftChatMessage.fromComponent(this.objective.m_83322_());
    }

    @Override
    public void setDisplayName(String displayName) {
        Preconditions.checkArgument((displayName != null ? 1 : 0) != 0, (Object)"Display name cannot be null");
        this.checkState();
        this.objective.m_83316_(CraftChatMessage.fromString(displayName)[0]);
    }

    @Override
    public String getCriteria() {
        this.checkState();
        return this.criteria.bukkitName;
    }

    @Override
    public Criteria getTrackedCriteria() {
        this.checkState();
        return this.criteria;
    }

    @Override
    public boolean isModifiable() {
        this.checkState();
        return !this.criteria.criteria.m_83621_();
    }

    @Override
    public void setDisplaySlot(DisplaySlot slot) {
        CraftScoreboard scoreboard = this.checkState();
        Scoreboard board = scoreboard.board;
        Objective objective = this.objective;
        int i = 0;
        while (i < 19) {
            if (board.m_83416_(i) == objective) {
                board.m_7136_(i, null);
            }
            ++i;
        }
        if (slot != null) {
            int slotNumber = CraftScoreboardTranslations.fromBukkitSlot(slot);
            board.m_7136_(slotNumber, this.getHandle());
        }
    }

    @Override
    public DisplaySlot getDisplaySlot() {
        CraftScoreboard scoreboard = this.checkState();
        Scoreboard board = scoreboard.board;
        Objective objective = this.objective;
        int i = 0;
        while (i < 19) {
            if (board.m_83416_(i) == objective) {
                return CraftScoreboardTranslations.toBukkitSlot(i);
            }
            ++i;
        }
        return null;
    }

    @Override
    public void setRenderType(RenderType renderType) {
        Preconditions.checkArgument((renderType != null ? 1 : 0) != 0, (Object)"RenderType cannot be null");
        this.checkState();
        this.objective.m_83314_(CraftScoreboardTranslations.fromBukkitRender(renderType));
    }

    @Override
    public RenderType getRenderType() {
        this.checkState();
        return CraftScoreboardTranslations.toBukkitRender(this.objective.m_83324_());
    }

    @Override
    public Score getScore(OfflinePlayer player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"Player cannot be null");
        this.checkState();
        return new CraftScore(this, player.getName());
    }

    @Override
    public Score getScore(String entry) {
        Preconditions.checkArgument((entry != null ? 1 : 0) != 0, (Object)"Entry cannot be null");
        Preconditions.checkArgument((entry.length() <= Short.MAX_VALUE ? 1 : 0) != 0, (Object)("Score '" + entry + "' is longer than the limit of 32767 characters"));
        this.checkState();
        return new CraftScore(this, entry);
    }

    @Override
    public void unregister() {
        CraftScoreboard scoreboard = this.checkState();
        scoreboard.board.m_83502_(this.objective);
    }

    @Override
    CraftScoreboard checkState() {
        Preconditions.checkState((((CraftScoreboard)this.getScoreboard()).board.m_83477_(this.objective.m_83320_()) != null ? 1 : 0) != 0, (Object)"Unregistered scoreboard component");
        return this.getScoreboard();
    }

    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.objective != null ? this.objective.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CraftObjective other = (CraftObjective)obj;
        return this.objective == other.objective || this.objective != null && this.objective.equals(other.objective);
    }
}

