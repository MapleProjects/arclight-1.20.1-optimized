/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.advancement;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public enum AdvancementDisplayType {
    TASK(ChatColor.GREEN),
    CHALLENGE(ChatColor.DARK_PURPLE),
    GOAL(ChatColor.GREEN);

    private final ChatColor color;

    private AdvancementDisplayType(ChatColor color) {
        this.color = color;
    }

    @NotNull
    public ChatColor getColor() {
        return this.color;
    }
}

