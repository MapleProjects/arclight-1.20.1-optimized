/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.jetbrains.annotations.Nullable;

public interface TNTPrimed
extends Explosive {
    public void setFuseTicks(int var1);

    public int getFuseTicks();

    @Nullable
    public Entity getSource();

    public void setSource(@Nullable Entity var1);
}

