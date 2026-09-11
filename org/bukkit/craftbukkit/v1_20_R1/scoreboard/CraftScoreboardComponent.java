/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import org.bukkit.craftbukkit.v1_20_R1.scoreboard.CraftScoreboard;

abstract class CraftScoreboardComponent {
    private CraftScoreboard scoreboard;

    CraftScoreboardComponent(CraftScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    abstract CraftScoreboard checkState();

    public CraftScoreboard getScoreboard() {
        return this.scoreboard;
    }

    abstract void unregister();
}

