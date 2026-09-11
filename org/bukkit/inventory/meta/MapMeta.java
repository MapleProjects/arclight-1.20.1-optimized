/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import org.bukkit.Color;
import org.bukkit.UndefinedNullability;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MapMeta
extends ItemMeta {
    @Deprecated
    public boolean hasMapId();

    @Deprecated
    public int getMapId();

    @Deprecated
    public void setMapId(int var1);

    public boolean hasMapView();

    @Nullable
    public MapView getMapView();

    public void setMapView(@UndefinedNullability(value="implementation defined") @UndefinedNullability(value="implementation defined") MapView var1);

    public boolean isScaling();

    public void setScaling(boolean var1);

    @Deprecated
    public boolean hasLocationName();

    @Deprecated
    @Nullable
    public String getLocationName();

    @Deprecated
    public void setLocationName(@Nullable String var1);

    public boolean hasColor();

    @Nullable
    public Color getColor();

    public void setColor(@Nullable Color var1);

    @Override
    @NotNull
    public MapMeta clone();
}

