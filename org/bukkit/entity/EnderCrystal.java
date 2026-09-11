/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface EnderCrystal
extends Entity {
    public boolean isShowingBottom();

    public void setShowingBottom(boolean var1);

    @Nullable
    public Location getBeamTarget();

    public void setBeamTarget(@Nullable Location var1);
}

