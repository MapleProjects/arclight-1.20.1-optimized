/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

public abstract class MapRenderer {
    private boolean contextual;

    public MapRenderer() {
        this(false);
    }

    public MapRenderer(boolean contextual) {
        this.contextual = contextual;
    }

    public final boolean isContextual() {
        return this.contextual;
    }

    public void initialize(@NotNull MapView map) {
    }

    public abstract void render(@NotNull MapView var1, @NotNull MapCanvas var2, @NotNull Player var3);
}

