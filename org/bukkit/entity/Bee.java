/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.jetbrains.annotations.Nullable;

public interface Bee
extends Animals {
    @Nullable
    public Location getHive();

    public void setHive(@Nullable Location var1);

    @Nullable
    public Location getFlower();

    public void setFlower(@Nullable Location var1);

    public boolean hasNectar();

    public void setHasNectar(boolean var1);

    public boolean hasStung();

    public void setHasStung(boolean var1);

    public int getAnger();

    public void setAnger(int var1);

    public int getCannotEnterHiveTicks();

    public void setCannotEnterHiveTicks(int var1);
}

