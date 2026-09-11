/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.hanging;

import org.bukkit.entity.Hanging;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class HangingEvent
extends Event {
    protected Hanging hanging;

    protected HangingEvent(@NotNull Hanging painting) {
        this.hanging = painting;
    }

    @NotNull
    public Hanging getEntity() {
        return this.hanging;
    }
}

