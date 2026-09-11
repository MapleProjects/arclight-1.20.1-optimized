/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Vehicle
extends Entity {
    @Override
    @NotNull
    public Vector getVelocity();

    @Override
    public void setVelocity(@NotNull Vector var1);
}

