/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface LightningStrike
extends Entity {
    public boolean isEffect();

    @Override
    @NotNull
    public Spigot spigot();

    public static class Spigot
    extends Entity.Spigot {
        public boolean isSilent() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

