/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableBiMap
 *  net.minecraft.world.scores.Scoreboard
 *  net.minecraft.world.scores.criteria.ObjectiveCriteria$RenderType
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;

final class CraftScoreboardTranslations {
    static final int MAX_DISPLAY_SLOT = 19;
    static final ImmutableBiMap<DisplaySlot, String> SLOTS = ImmutableBiMap.builder().put((Object)DisplaySlot.BELOW_NAME, (Object)"belowName").put((Object)DisplaySlot.PLAYER_LIST, (Object)"list").put((Object)DisplaySlot.SIDEBAR, (Object)"sidebar").put((Object)DisplaySlot.SIDEBAR_BLACK, (Object)"sidebar.team.black").put((Object)DisplaySlot.SIDEBAR_DARK_BLUE, (Object)"sidebar.team.dark_blue").put((Object)DisplaySlot.SIDEBAR_DARK_GREEN, (Object)"sidebar.team.dark_green").put((Object)DisplaySlot.SIDEBAR_DARK_AQUA, (Object)"sidebar.team.dark_aqua").put((Object)DisplaySlot.SIDEBAR_DARK_RED, (Object)"sidebar.team.dark_red").put((Object)DisplaySlot.SIDEBAR_DARK_PURPLE, (Object)"sidebar.team.dark_purple").put((Object)DisplaySlot.SIDEBAR_GOLD, (Object)"sidebar.team.gold").put((Object)DisplaySlot.SIDEBAR_GRAY, (Object)"sidebar.team.gray").put((Object)DisplaySlot.SIDEBAR_DARK_GRAY, (Object)"sidebar.team.dark_gray").put((Object)DisplaySlot.SIDEBAR_BLUE, (Object)"sidebar.team.blue").put((Object)DisplaySlot.SIDEBAR_GREEN, (Object)"sidebar.team.green").put((Object)DisplaySlot.SIDEBAR_AQUA, (Object)"sidebar.team.aqua").put((Object)DisplaySlot.SIDEBAR_RED, (Object)"sidebar.team.red").put((Object)DisplaySlot.SIDEBAR_LIGHT_PURPLE, (Object)"sidebar.team.light_purple").put((Object)DisplaySlot.SIDEBAR_YELLOW, (Object)"sidebar.team.yellow").put((Object)DisplaySlot.SIDEBAR_WHITE, (Object)"sidebar.team.white").buildOrThrow();

    private CraftScoreboardTranslations() {
    }

    static DisplaySlot toBukkitSlot(int i) {
        return (DisplaySlot)((Object)SLOTS.inverse().get((Object)Scoreboard.m_83453_((int)i)));
    }

    static int fromBukkitSlot(DisplaySlot slot) {
        return Scoreboard.m_83504_((String)((String)SLOTS.get((Object)slot)));
    }

    static RenderType toBukkitRender(ObjectiveCriteria.RenderType display) {
        return RenderType.valueOf(display.name());
    }

    static ObjectiveCriteria.RenderType fromBukkitRender(RenderType render) {
        return ObjectiveCriteria.RenderType.valueOf((String)render.name());
    }
}

