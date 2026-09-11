/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.scoreboard;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.scoreboard.RenderType;
import org.jetbrains.annotations.NotNull;

public interface Criteria {
    public static final Criteria DUMMY = Bukkit.getScoreboardCriteria("dummy");
    public static final Criteria TRIGGER = Bukkit.getScoreboardCriteria("trigger");
    public static final Criteria DEATH_COUNT = Bukkit.getScoreboardCriteria("deathCount");
    public static final Criteria PLAYER_KILL_COUNT = Bukkit.getScoreboardCriteria("playerKillCount");
    public static final Criteria TOTAL_KILL_COUNT = Bukkit.getScoreboardCriteria("totalKillCount");
    public static final Criteria HEALTH = Bukkit.getScoreboardCriteria("health");
    public static final Criteria FOOD = Bukkit.getScoreboardCriteria("food");
    public static final Criteria AIR = Bukkit.getScoreboardCriteria("air");
    public static final Criteria ARMOR = Bukkit.getScoreboardCriteria("armor");
    public static final Criteria XP = Bukkit.getScoreboardCriteria("xp");
    public static final Criteria LEVEL = Bukkit.getScoreboardCriteria("level");
    public static final Criteria TEAM_KILL_BLACK = Bukkit.getScoreboardCriteria("teamkill.black");
    public static final Criteria TEAM_KILL_DARK_BLUE = Bukkit.getScoreboardCriteria("teamkill.dark_blue");
    public static final Criteria TEAM_KILL_DARK_GREEN = Bukkit.getScoreboardCriteria("teamkill.dark_green");
    public static final Criteria TEAM_KILL_DARK_AQUA = Bukkit.getScoreboardCriteria("teamkill.dark_aqua");
    public static final Criteria TEAM_KILL_DARK_RED = Bukkit.getScoreboardCriteria("teamkill.dark_red");
    public static final Criteria TEAM_KILL_DARK_PURPLE = Bukkit.getScoreboardCriteria("teamkill.dark_purple");
    public static final Criteria TEAM_KILL_GOLD = Bukkit.getScoreboardCriteria("teamkill.gold");
    public static final Criteria TEAM_KILL_GRAY = Bukkit.getScoreboardCriteria("teamkill.gray");
    public static final Criteria TEAM_KILL_DARK_GRAY = Bukkit.getScoreboardCriteria("teamkill.dark_gray");
    public static final Criteria TEAM_KILL_BLUE = Bukkit.getScoreboardCriteria("teamkill.blue");
    public static final Criteria TEAM_KILL_GREEN = Bukkit.getScoreboardCriteria("teamkill.green");
    public static final Criteria TEAM_KILL_AQUA = Bukkit.getScoreboardCriteria("teamkill.aqua");
    public static final Criteria TEAM_KILL_RED = Bukkit.getScoreboardCriteria("teamkill.red");
    public static final Criteria TEAM_KILL_LIGHT_PURPLE = Bukkit.getScoreboardCriteria("teamkill.light_purple");
    public static final Criteria TEAM_KILL_YELLOW = Bukkit.getScoreboardCriteria("teamkill.yellow");
    public static final Criteria TEAM_KILL_WHITE = Bukkit.getScoreboardCriteria("teamkill.white");
    public static final Criteria KILLED_BY_TEAM_BLACK = Bukkit.getScoreboardCriteria("killedByTeam.black");
    public static final Criteria KILLED_BY_TEAM_DARK_BLUE = Bukkit.getScoreboardCriteria("killedByTeam.dark_blue");
    public static final Criteria KILLED_BY_TEAM_DARK_GREEN = Bukkit.getScoreboardCriteria("killedByTeam.dark_green");
    public static final Criteria KILLED_BY_TEAM_DARK_AQUA = Bukkit.getScoreboardCriteria("killedByTeam.dark_aqua");
    public static final Criteria KILLED_BY_TEAM_DARK_RED = Bukkit.getScoreboardCriteria("killedByTeam.dark_red");
    public static final Criteria KILLED_BY_TEAM_DARK_PURPLE = Bukkit.getScoreboardCriteria("killedByTeam.dark_purple");
    public static final Criteria KILLED_BY_TEAM_GOLD = Bukkit.getScoreboardCriteria("killedByTeam.gold");
    public static final Criteria KILLED_BY_TEAM_GRAY = Bukkit.getScoreboardCriteria("killedByTeam.gray");
    public static final Criteria KILLED_BY_TEAM_DARK_GRAY = Bukkit.getScoreboardCriteria("killedByTeam.dark_gray");
    public static final Criteria KILLED_BY_TEAM_BLUE = Bukkit.getScoreboardCriteria("killedByTeam.blue");
    public static final Criteria KILLED_BY_TEAM_GREEN = Bukkit.getScoreboardCriteria("killedByTeam.green");
    public static final Criteria KILLED_BY_TEAM_AQUA = Bukkit.getScoreboardCriteria("killedByTeam.aqua");
    public static final Criteria KILLED_BY_TEAM_RED = Bukkit.getScoreboardCriteria("killedByTeam.red");
    public static final Criteria KILLED_BY_TEAM_LIGHT_PURPLE = Bukkit.getScoreboardCriteria("killedByTeam.light_purple");
    public static final Criteria KILLED_BY_TEAM_YELLOW = Bukkit.getScoreboardCriteria("killedByTeam.yellow");
    public static final Criteria KILLED_BY_TEAM_WHITE = Bukkit.getScoreboardCriteria("killedByTeam.white");
    public static final /* synthetic */ int[] $SWITCH_TABLE$org$bukkit$Statistic;

    @NotNull
    public String getName();

    public boolean isReadOnly();

    @NotNull
    public RenderType getDefaultRenderType();

    @NotNull
    public static Criteria statistic(@NotNull Statistic statistic, @NotNull Material material) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"statistic must not be null");
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material must not be null");
        Statistic.Type type = statistic.getType();
        Preconditions.checkArgument((type == Statistic.Type.BLOCK || type == Statistic.Type.ITEM ? 1 : 0) != 0, (String)"statistic type must be either BLOCK or ITEM, given %s", (Object)((Object)type));
        Preconditions.checkArgument((type != Statistic.Type.BLOCK || material.isBlock() ? 1 : 0) != 0, (String)"statistic type is BLOCK but got non-block Material, %s", (Object)material);
        Preconditions.checkArgument((type != Statistic.Type.ITEM || material.isItem() ? 1 : 0) != 0, (String)"statistic type is ITEM but got non-item Material, %s", (Object)material);
        if (type == Statistic.Type.BLOCK) {
            switch (statistic) {
                case MINE_BLOCK: {
                    return Bukkit.getScoreboardCriteria("minecraft.mined:minecraft." + material.getKey().getKey());
                }
            }
        } else if (type == Statistic.Type.ITEM) {
            switch (statistic) {
                case BREAK_ITEM: {
                    return Bukkit.getScoreboardCriteria("minecraft.broken:minecraft." + material.getKey().getKey());
                }
                case CRAFT_ITEM: {
                    return Bukkit.getScoreboardCriteria("minecraft.crafted:minecraft." + material.getKey().getKey());
                }
                case USE_ITEM: {
                    return Bukkit.getScoreboardCriteria("minecraft.used:minecraft." + material.getKey().getKey());
                }
                case PICKUP: {
                    return Bukkit.getScoreboardCriteria("minecraft.picked_up:minecraft." + material.getKey().getKey());
                }
                case DROP: {
                    return Bukkit.getScoreboardCriteria("minecraft.dropped:minecraft." + material.getKey().getKey());
                }
            }
        }
        return Criteria.statistic(statistic);
    }

    @NotNull
    public static Criteria statistic(@NotNull Statistic statistic, @NotNull EntityType entityType) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"statistic must not be null");
        Preconditions.checkArgument((entityType != null ? 1 : 0) != 0, (Object)"entityType must not be null");
        Preconditions.checkArgument((statistic.getType() == Statistic.Type.ENTITY ? 1 : 0) != 0, (String)"statistic type must be ENTITY, given %s", (Object)((Object)statistic.getType()));
        switch (statistic) {
            case KILL_ENTITY: {
                return Bukkit.getScoreboardCriteria("minecraft.killed:minecraft." + entityType.getKey().getKey());
            }
            case ENTITY_KILLED_BY: {
                return Bukkit.getScoreboardCriteria("minecraft.killed_by:minecraft." + entityType.getKey().getKey());
            }
        }
        return Criteria.statistic(statistic);
    }

    @NotNull
    public static Criteria statistic(@NotNull Statistic statistic) {
        Preconditions.checkArgument((statistic != null ? 1 : 0) != 0, (Object)"statistic must not be null");
        return Bukkit.getScoreboardCriteria("minecraft.custom:minecraft." + statistic.getKey().getKey());
    }

    @NotNull
    public static Criteria create(@NotNull String name) {
        return Bukkit.getScoreboardCriteria(name);
    }
}

