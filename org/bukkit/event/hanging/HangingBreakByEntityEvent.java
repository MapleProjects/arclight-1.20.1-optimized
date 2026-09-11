/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.hanging;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HangingBreakByEntityEvent
extends HangingBreakEvent {
    private final Entity remover;

    public HangingBreakByEntityEvent(@NotNull Hanging hanging, @Nullable Entity remover) {
        this(hanging, remover, HangingBreakEvent.RemoveCause.ENTITY);
    }

    public HangingBreakByEntityEvent(@NotNull Hanging hanging, @Nullable Entity remover, @NotNull HangingBreakEvent.RemoveCause cause) {
        super(hanging, cause);
        this.remover = remover;
    }

    @Nullable
    public Entity getRemover() {
        return this.remover;
    }
}

