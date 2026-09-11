/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.joml.AxisAngle4f
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 */
package org.bukkit.util;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transformation {
    @NotNull
    private final Vector3f translation;
    @NotNull
    private final Quaternionf leftRotation;
    @NotNull
    private final Vector3f scale;
    @NotNull
    private final Quaternionf rightRotation;

    public Transformation(@NotNull Vector3f translation, @NotNull AxisAngle4f leftRotation, @NotNull Vector3f scale, @NotNull AxisAngle4f rightRotation) {
        Preconditions.checkArgument((translation != null ? 1 : 0) != 0, (Object)"translation cannot be null");
        Preconditions.checkArgument((leftRotation != null ? 1 : 0) != 0, (Object)"leftRotation cannot be null");
        Preconditions.checkArgument((scale != null ? 1 : 0) != 0, (Object)"scale cannot be null");
        Preconditions.checkArgument((rightRotation != null ? 1 : 0) != 0, (Object)"rightRotation cannot be null");
        this.translation = translation;
        this.leftRotation = new Quaternionf(leftRotation);
        this.scale = scale;
        this.rightRotation = new Quaternionf(rightRotation);
    }

    public Transformation(@NotNull Vector3f translation, @NotNull Quaternionf leftRotation, @NotNull Vector3f scale, @NotNull Quaternionf rightRotation) {
        Preconditions.checkArgument((translation != null ? 1 : 0) != 0, (Object)"translation cannot be null");
        Preconditions.checkArgument((leftRotation != null ? 1 : 0) != 0, (Object)"leftRotation cannot be null");
        Preconditions.checkArgument((scale != null ? 1 : 0) != 0, (Object)"scale cannot be null");
        Preconditions.checkArgument((rightRotation != null ? 1 : 0) != 0, (Object)"rightRotation cannot be null");
        this.translation = translation;
        this.leftRotation = leftRotation;
        this.scale = scale;
        this.rightRotation = rightRotation;
    }

    @NotNull
    public Vector3f getTranslation() {
        return this.translation;
    }

    @NotNull
    public Quaternionf getLeftRotation() {
        return this.leftRotation;
    }

    @NotNull
    public Vector3f getScale() {
        return this.scale;
    }

    @NotNull
    public Quaternionf getRightRotation() {
        return this.rightRotation;
    }

    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.translation);
        hash = 11 * hash + Objects.hashCode(this.leftRotation);
        hash = 11 * hash + Objects.hashCode(this.scale);
        hash = 11 * hash + Objects.hashCode(this.rightRotation);
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
        Transformation other = (Transformation)obj;
        if (!Objects.equals(this.translation, other.translation)) {
            return false;
        }
        if (!Objects.equals(this.leftRotation, other.leftRotation)) {
            return false;
        }
        if (!Objects.equals(this.scale, other.scale)) {
            return false;
        }
        return Objects.equals(this.rightRotation, other.rightRotation);
    }

    public String toString() {
        return "Transformation{translation=" + this.translation + ", leftRotation=" + this.leftRotation + ", scale=" + this.scale + ", rightRotation=" + this.rightRotation + '}';
    }
}

