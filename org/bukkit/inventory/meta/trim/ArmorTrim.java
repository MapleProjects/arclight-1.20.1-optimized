/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta.trim;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.jetbrains.annotations.NotNull;

public class ArmorTrim {
    private final TrimMaterial material;
    private final TrimPattern pattern;

    public ArmorTrim(@NotNull TrimMaterial material, @NotNull TrimPattern pattern) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material must not be null");
        Preconditions.checkArgument((pattern != null ? 1 : 0) != 0, (Object)"pattern must not be null");
        this.material = material;
        this.pattern = pattern;
    }

    @NotNull
    public TrimMaterial getMaterial() {
        return this.material;
    }

    @NotNull
    public TrimPattern getPattern() {
        return this.pattern;
    }

    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.material);
        hash = 31 * hash + Objects.hashCode(this.pattern);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ArmorTrim)) {
            return false;
        }
        ArmorTrim other = (ArmorTrim)obj;
        return this.material == other.material && this.pattern == other.pattern;
    }
}

