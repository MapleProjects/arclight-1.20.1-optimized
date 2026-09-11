/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.banner;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.PatternType;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

@SerializableAs(value="Pattern")
public class Pattern
implements ConfigurationSerializable {
    private static final String COLOR = "color";
    private static final String PATTERN = "pattern";
    private final DyeColor color;
    private final PatternType pattern;

    public Pattern(@NotNull DyeColor color, @NotNull PatternType pattern) {
        this.color = color;
        this.pattern = pattern;
    }

    public Pattern(@NotNull Map<String, Object> map) {
        this.color = DyeColor.legacyValueOf(Pattern.getString(map, COLOR));
        this.pattern = PatternType.getByIdentifier(Pattern.getString(map, PATTERN));
    }

    private static String getString(@NotNull Map<?, ?> map, @NotNull Object key) {
        Object str = map.get(key);
        if (str instanceof String) {
            return (String)str;
        }
        throw new NoSuchElementException(map + " does not contain " + key);
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        return ImmutableMap.of((Object)COLOR, (Object)this.color.toString(), (Object)PATTERN, (Object)this.pattern.getIdentifier());
    }

    @NotNull
    public DyeColor getColor() {
        return this.color;
    }

    @NotNull
    public PatternType getPattern() {
        return this.pattern;
    }

    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.color != null ? this.color.hashCode() : 0);
        hash = 97 * hash + (this.pattern != null ? this.pattern.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Pattern other = (Pattern)obj;
        return this.color == other.color && this.pattern == other.pattern;
    }
}

