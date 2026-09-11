/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.map;

import java.util.List;
import org.bukkit.World;
import org.bukkit.map.MapRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MapView {
    public int getId();

    public boolean isVirtual();

    @NotNull
    public Scale getScale();

    public void setScale(@NotNull Scale var1);

    public int getCenterX();

    public int getCenterZ();

    public void setCenterX(int var1);

    public void setCenterZ(int var1);

    @Nullable
    public World getWorld();

    public void setWorld(@NotNull World var1);

    @NotNull
    public List<MapRenderer> getRenderers();

    public void addRenderer(@NotNull MapRenderer var1);

    public boolean removeRenderer(@Nullable MapRenderer var1);

    public boolean isTrackingPosition();

    public void setTrackingPosition(boolean var1);

    public boolean isUnlimitedTracking();

    public void setUnlimitedTracking(boolean var1);

    public boolean isLocked();

    public void setLocked(boolean var1);

    public static enum Scale {
        CLOSEST(0),
        CLOSE(1),
        NORMAL(2),
        FAR(3),
        FARTHEST(4);

        private byte value;

        private Scale(int value) {
            this.value = (byte)value;
        }

        @Deprecated
        public byte getValue() {
            return this.value;
        }

        public static Scale valueOf(String string) {
            return Enum.valueOf(Scale.class, string);
        }
    }
}

