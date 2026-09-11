/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.scoreboard;

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Team {
    @NotNull
    public String getName();

    @NotNull
    public String getDisplayName();

    public void setDisplayName(@NotNull String var1);

    @NotNull
    public String getPrefix();

    public void setPrefix(@NotNull String var1);

    @NotNull
    public String getSuffix();

    public void setSuffix(@NotNull String var1);

    @NotNull
    public ChatColor getColor();

    public void setColor(@NotNull ChatColor var1);

    public boolean allowFriendlyFire();

    public void setAllowFriendlyFire(boolean var1);

    public boolean canSeeFriendlyInvisibles();

    public void setCanSeeFriendlyInvisibles(boolean var1);

    @Deprecated
    @NotNull
    public NameTagVisibility getNameTagVisibility();

    @Deprecated
    public void setNameTagVisibility(@NotNull NameTagVisibility var1);

    @Deprecated
    @NotNull
    public Set<OfflinePlayer> getPlayers();

    @NotNull
    public Set<String> getEntries();

    public int getSize();

    @Nullable
    public Scoreboard getScoreboard();

    @Deprecated
    public void addPlayer(@NotNull OfflinePlayer var1);

    public void addEntry(@NotNull String var1);

    @Deprecated
    public boolean removePlayer(@NotNull OfflinePlayer var1);

    public boolean removeEntry(@NotNull String var1);

    public void unregister();

    @Deprecated
    public boolean hasPlayer(@NotNull OfflinePlayer var1);

    public boolean hasEntry(@NotNull String var1);

    @NotNull
    public OptionStatus getOption(@NotNull Option var1);

    public void setOption(@NotNull Option var1, @NotNull OptionStatus var2);

    public static enum Option {
        NAME_TAG_VISIBILITY,
        DEATH_MESSAGE_VISIBILITY,
        COLLISION_RULE;

    }

    public static enum OptionStatus {
        ALWAYS,
        NEVER,
        FOR_OTHER_TEAMS,
        FOR_OWN_TEAM;

    }
}

