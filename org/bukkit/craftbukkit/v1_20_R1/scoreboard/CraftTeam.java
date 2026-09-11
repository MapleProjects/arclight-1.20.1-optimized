/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  net.minecraft.world.scores.PlayerTeam
 *  net.minecraft.world.scores.Team$CollisionRule
 *  net.minecraft.world.scores.Team$Visibility
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboard;
import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboardComponent;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

final class CraftTeam
extends CraftScoreboardComponent
implements Team {
    private final PlayerTeam team;

    CraftTeam(CraftScoreboard scoreboard, PlayerTeam team) {
        super(scoreboard);
        this.team = team;
    }

    @Override
    public String getName() {
        this.checkState();
        return this.team.m_5758_();
    }

    @Override
    public String getDisplayName() {
        this.checkState();
        return CraftChatMessage.fromComponent(this.team.m_83364_());
    }

    @Override
    public void setDisplayName(String displayName) {
        Preconditions.checkArgument((displayName != null ? 1 : 0) != 0, (Object)"Display name cannot be null");
        this.checkState();
        this.team.m_83353_(CraftChatMessage.fromString(displayName)[0]);
    }

    @Override
    public String getPrefix() {
        this.checkState();
        return CraftChatMessage.fromComponent(this.team.m_83370_());
    }

    @Override
    public void setPrefix(String prefix) {
        Preconditions.checkArgument((prefix != null ? 1 : 0) != 0, (Object)"Prefix cannot be null");
        this.checkState();
        this.team.m_83360_(CraftChatMessage.fromStringOrNull(prefix));
    }

    @Override
    public String getSuffix() {
        this.checkState();
        return CraftChatMessage.fromComponent(this.team.m_83371_());
    }

    @Override
    public void setSuffix(String suffix) {
        Preconditions.checkArgument((suffix != null ? 1 : 0) != 0, (Object)"Suffix cannot be null");
        this.checkState();
        this.team.m_83365_(CraftChatMessage.fromStringOrNull(suffix));
    }

    @Override
    public ChatColor getColor() {
        this.checkState();
        return CraftChatMessage.getColor(this.team.m_7414_());
    }

    @Override
    public void setColor(ChatColor color) {
        Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"Color cannot be null");
        this.checkState();
        this.team.m_83351_(CraftChatMessage.getColor(color));
    }

    @Override
    public boolean allowFriendlyFire() {
        this.checkState();
        return this.team.m_6260_();
    }

    @Override
    public void setAllowFriendlyFire(boolean enabled) {
        this.checkState();
        this.team.m_83355_(enabled);
    }

    @Override
    public boolean canSeeFriendlyInvisibles() {
        this.checkState();
        return this.team.m_6259_();
    }

    @Override
    public void setCanSeeFriendlyInvisibles(boolean enabled) {
        this.checkState();
        this.team.m_83362_(enabled);
    }

    @Override
    public NameTagVisibility getNameTagVisibility() throws IllegalArgumentException {
        this.checkState();
        return CraftTeam.notchToBukkit(this.team.m_7470_());
    }

    @Override
    public void setNameTagVisibility(NameTagVisibility visibility) throws IllegalArgumentException {
        this.checkState();
        this.team.m_83346_(CraftTeam.bukkitToNotch(visibility));
    }

    @Override
    public Set<OfflinePlayer> getPlayers() {
        this.checkState();
        ImmutableSet.Builder players = ImmutableSet.builder();
        for (String playerName : this.team.m_6809_()) {
            players.add((Object)Bukkit.getOfflinePlayer(playerName));
        }
        return players.build();
    }

    @Override
    public Set<String> getEntries() {
        this.checkState();
        ImmutableSet.Builder entries = ImmutableSet.builder();
        for (String playerName : this.team.m_6809_()) {
            entries.add((Object)playerName);
        }
        return entries.build();
    }

    @Override
    public int getSize() {
        this.checkState();
        return this.team.m_6809_().size();
    }

    @Override
    public void addPlayer(OfflinePlayer player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"OfflinePlayer cannot be null");
        this.addEntry(player.getName());
    }

    @Override
    public void addEntry(String entry) {
        Preconditions.checkArgument((entry != null ? 1 : 0) != 0, (Object)"Entry cannot be null");
        CraftScoreboard scoreboard = this.checkState();
        scoreboard.board.m_6546_(entry, this.team);
    }

    @Override
    public boolean removePlayer(OfflinePlayer player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"OfflinePlayer cannot be null");
        return this.removeEntry(player.getName());
    }

    @Override
    public boolean removeEntry(String entry) {
        Preconditions.checkArgument((entry != null ? 1 : 0) != 0, (Object)"Entry cannot be null");
        CraftScoreboard scoreboard = this.checkState();
        if (!this.team.m_6809_().contains(entry)) {
            return false;
        }
        scoreboard.board.m_6519_(entry, this.team);
        return true;
    }

    @Override
    public boolean hasPlayer(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"OfflinePlayer cannot be null");
        return this.hasEntry(player.getName());
    }

    @Override
    public boolean hasEntry(String entry) throws IllegalArgumentException, IllegalStateException {
        Preconditions.checkArgument((entry != null ? 1 : 0) != 0, (Object)"Entry cannot be null");
        this.checkState();
        return this.team.m_6809_().contains(entry);
    }

    @Override
    public void unregister() {
        CraftScoreboard scoreboard = this.checkState();
        scoreboard.board.m_83475_(this.team);
    }

    @Override
    public Team.OptionStatus getOption(Team.Option option) {
        this.checkState();
        switch (option) {
            case NAME_TAG_VISIBILITY: {
                return Team.OptionStatus.values()[this.team.m_7470_().ordinal()];
            }
            case DEATH_MESSAGE_VISIBILITY: {
                return Team.OptionStatus.values()[this.team.m_7468_().ordinal()];
            }
            case COLLISION_RULE: {
                return Team.OptionStatus.values()[this.team.m_7156_().ordinal()];
            }
        }
        throw new IllegalArgumentException("Unrecognised option " + (Object)((Object)option));
    }

    @Override
    public void setOption(Team.Option option, Team.OptionStatus status) {
        this.checkState();
        switch (option) {
            case NAME_TAG_VISIBILITY: {
                this.team.m_83346_(Team.Visibility.values()[status.ordinal()]);
                break;
            }
            case DEATH_MESSAGE_VISIBILITY: {
                this.team.m_83358_(Team.Visibility.values()[status.ordinal()]);
                break;
            }
            case COLLISION_RULE: {
                this.team.m_83344_(Team.CollisionRule.values()[status.ordinal()]);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unrecognised option " + (Object)((Object)option));
            }
        }
    }

    public static Team.Visibility bukkitToNotch(NameTagVisibility visibility) {
        switch (visibility) {
            case ALWAYS: {
                return Team.Visibility.ALWAYS;
            }
            case NEVER: {
                return Team.Visibility.NEVER;
            }
            case HIDE_FOR_OTHER_TEAMS: {
                return Team.Visibility.HIDE_FOR_OTHER_TEAMS;
            }
            case HIDE_FOR_OWN_TEAM: {
                return Team.Visibility.HIDE_FOR_OWN_TEAM;
            }
        }
        throw new IllegalArgumentException("Unknown visibility level " + (Object)((Object)visibility));
    }

    public static NameTagVisibility notchToBukkit(Team.Visibility visibility) {
        switch (visibility) {
            case ALWAYS: {
                return NameTagVisibility.ALWAYS;
            }
            case NEVER: {
                return NameTagVisibility.NEVER;
            }
            case HIDE_FOR_OTHER_TEAMS: {
                return NameTagVisibility.HIDE_FOR_OTHER_TEAMS;
            }
            case HIDE_FOR_OWN_TEAM: {
                return NameTagVisibility.HIDE_FOR_OWN_TEAM;
            }
        }
        throw new IllegalArgumentException("Unknown visibility level " + visibility);
    }

    @Override
    CraftScoreboard checkState() {
        Preconditions.checkState((((CraftScoreboard)this.getScoreboard()).board.m_83489_(this.team.m_5758_()) != null ? 1 : 0) != 0, (Object)"Unregistered scoreboard component");
        return this.getScoreboard();
    }

    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.team != null ? this.team.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        CraftTeam other = (CraftTeam)obj;
        return this.team == other.team || this.team != null && this.team.equals(other.team);
    }
}

