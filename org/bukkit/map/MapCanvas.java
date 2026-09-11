/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.map;

import java.awt.Color;
import java.awt.Image;
import org.bukkit.map.MapCursorCollection;
import org.bukkit.map.MapFont;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MapCanvas {
    @NotNull
    public MapView getMapView();

    @NotNull
    public MapCursorCollection getCursors();

    public void setCursors(@NotNull MapCursorCollection var1);

    public void setPixelColor(int var1, int var2, @Nullable Color var3);

    @Nullable
    public Color getPixelColor(int var1, int var2);

    @NotNull
    public Color getBasePixelColor(int var1, int var2);

    public void setPixel(int var1, int var2, byte var3);

    @Deprecated
    public byte getPixel(int var1, int var2);

    @Deprecated
    public byte getBasePixel(int var1, int var2);

    public void drawImage(int var1, int var2, @NotNull Image var3);

    public void drawText(int var1, int var2, @NotNull MapFont var3, @NotNull String var4);
}

