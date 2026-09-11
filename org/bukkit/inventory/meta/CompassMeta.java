/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import org.bukkit.Location;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

public interface CompassMeta
extends ItemMeta {
    public boolean hasLodestone();

    @Nullable
    public Location getLodestone();

    public void setLodestone(@Nullable Location var1);

    public boolean isLodestoneTracked();

    public void setLodestoneTracked(boolean var1);

    @Override
    public CompassMeta clone();
}

