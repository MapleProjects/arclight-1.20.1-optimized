/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.block;

import org.bukkit.Location;
import org.bukkit.block.EntityBlockStorage;
import org.bukkit.entity.Bee;
import org.jetbrains.annotations.Nullable;

public interface Beehive
extends EntityBlockStorage<Bee> {
    @Nullable
    public Location getFlower();

    public void setFlower(@Nullable Location var1);

    public boolean isSedated();
}

