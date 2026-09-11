/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 */
package org.bukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public interface Display
extends Entity {
    @NotNull
    public Transformation getTransformation();

    public void setTransformation(@NotNull Transformation var1);

    public void setTransformationMatrix(@NotNull Matrix4f var1);

    public int getInterpolationDuration();

    public void setInterpolationDuration(int var1);

    public float getViewRange();

    public void setViewRange(float var1);

    public float getShadowRadius();

    public void setShadowRadius(float var1);

    public float getShadowStrength();

    public void setShadowStrength(float var1);

    public float getDisplayWidth();

    public void setDisplayWidth(float var1);

    public float getDisplayHeight();

    public void setDisplayHeight(float var1);

    public int getInterpolationDelay();

    public void setInterpolationDelay(int var1);

    @NotNull
    public Billboard getBillboard();

    public void setBillboard(@NotNull Billboard var1);

    @Nullable
    public Color getGlowColorOverride();

    public void setGlowColorOverride(@Nullable Color var1);

    @Nullable
    public Brightness getBrightness();

    public void setBrightness(@Nullable Brightness var1);

    public static enum Billboard {
        FIXED,
        VERTICAL,
        HORIZONTAL,
        CENTER;

    }

    public static class Brightness {
        private final int blockLight;
        private final int skyLight;

        public Brightness(int blockLight, int skyLight) {
            Preconditions.checkArgument((blockLight >= 0 && blockLight <= 15 ? 1 : 0) != 0, (String)"Block brightness out of range: %s", (int)blockLight);
            Preconditions.checkArgument((skyLight >= 0 && skyLight <= 15 ? 1 : 0) != 0, (String)"Sky brightness out of range: %s", (int)skyLight);
            this.blockLight = blockLight;
            this.skyLight = skyLight;
        }

        public int getBlockLight() {
            return this.blockLight;
        }

        public int getSkyLight() {
            return this.skyLight;
        }

        public int hashCode() {
            int hash = 7;
            hash = 47 * hash + this.blockLight;
            hash = 47 * hash + this.skyLight;
            return hash;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            Brightness other = (Brightness)obj;
            if (this.blockLight != other.blockLight) {
                return false;
            }
            return this.skyLight == other.skyLight;
        }

        public String toString() {
            return "Brightness{blockLight=" + this.blockLight + ", skyLight=" + this.skyLight + '}';
        }
    }
}

