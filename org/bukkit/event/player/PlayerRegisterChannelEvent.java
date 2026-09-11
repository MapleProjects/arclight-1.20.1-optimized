/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChannelEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerRegisterChannelEvent
extends PlayerChannelEvent {
    public PlayerRegisterChannelEvent(@NotNull Player player, @NotNull String channel) {
        super(player, channel);
    }
}

