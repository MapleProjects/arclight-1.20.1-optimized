/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerEvent
extends Event {
    protected Player player;

    public PlayerEvent(@NotNull Player who) {
        this.player = who;
    }

    PlayerEvent(@NotNull Player who, boolean async) {
        super(async);
        this.player = who;
    }

    @NotNull
    public final Player getPlayer() {
        return this.player;
    }
}

