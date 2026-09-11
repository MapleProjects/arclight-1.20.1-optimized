/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Color;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

@SerializableAs(value="Firework")
public final class FireworkEffect
implements ConfigurationSerializable {
    private static final String FLICKER = "flicker";
    private static final String TRAIL = "trail";
    private static final String COLORS = "colors";
    private static final String FADE_COLORS = "fade-colors";
    private static final String TYPE = "type";
    private final boolean flicker;
    private final boolean trail;
    private final ImmutableList<Color> colors;
    private final ImmutableList<Color> fadeColors;
    private final Type type;
    private String string = null;

    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    FireworkEffect(boolean flicker, boolean trail, @NotNull ImmutableList<Color> colors, @NotNull ImmutableList<Color> fadeColors, @NotNull Type type) {
        if (colors.isEmpty()) {
            throw new IllegalStateException("Cannot make FireworkEffect without any color");
        }
        this.flicker = flicker;
        this.trail = trail;
        this.colors = colors;
        this.fadeColors = fadeColors;
        this.type = type;
    }

    public boolean hasFlicker() {
        return this.flicker;
    }

    public boolean hasTrail() {
        return this.trail;
    }

    @NotNull
    public List<Color> getColors() {
        return this.colors;
    }

    @NotNull
    public List<Color> getFadeColors() {
        return this.fadeColors;
    }

    @NotNull
    public Type getType() {
        return this.type;
    }

    @NotNull
    public static ConfigurationSerializable deserialize(@NotNull Map<String, Object> map) {
        Type type = Type.valueOf((String)map.get(TYPE));
        return FireworkEffect.builder().flicker((Boolean)map.get(FLICKER)).trail((Boolean)map.get(TRAIL)).withColor((Iterable)map.get(COLORS)).withFade((Iterable)map.get(FADE_COLORS)).with(type).build();
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        return ImmutableMap.of((Object)FLICKER, (Object)this.flicker, (Object)TRAIL, (Object)this.trail, (Object)COLORS, this.colors, (Object)FADE_COLORS, this.fadeColors, (Object)TYPE, (Object)this.type.name());
    }

    public String toString() {
        String string = this.string;
        if (string == null) {
            this.string = "FireworkEffect:" + this.serialize();
            return this.string;
        }
        return string;
    }

    public int hashCode() {
        int PRIME = 31;
        int TRUE = 1231;
        int FALSE = 1237;
        int hash = 1;
        hash = hash * 31 + (this.flicker ? 1231 : 1237);
        hash = hash * 31 + (this.trail ? 1231 : 1237);
        hash = hash * 31 + this.type.hashCode();
        hash = hash * 31 + this.colors.hashCode();
        hash = hash * 31 + this.fadeColors.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FireworkEffect)) {
            return false;
        }
        FireworkEffect that = (FireworkEffect)obj;
        return this.flicker == that.flicker && this.trail == that.trail && this.type == that.type && this.colors.equals(that.colors) && this.fadeColors.equals(that.fadeColors);
    }

    public static final class Builder {
        boolean flicker = false;
        boolean trail = false;
        final ImmutableList.Builder<Color> colors = ImmutableList.builder();
        ImmutableList.Builder<Color> fadeColors = null;
        Type type = Type.BALL;

        Builder() {
        }

        @NotNull
        public Builder with(@NotNull Type type) throws IllegalArgumentException {
            Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Cannot have null type");
            this.type = type;
            return this;
        }

        @NotNull
        public Builder withFlicker() {
            this.flicker = true;
            return this;
        }

        @NotNull
        public Builder flicker(boolean flicker) {
            this.flicker = flicker;
            return this;
        }

        @NotNull
        public Builder withTrail() {
            this.trail = true;
            return this;
        }

        @NotNull
        public Builder trail(boolean trail) {
            this.trail = trail;
            return this;
        }

        @NotNull
        public Builder withColor(@NotNull Color color) throws IllegalArgumentException {
            Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"Cannot have null color");
            this.colors.add((Object)color);
            return this;
        }

        @NotNull
        public Builder withColor(Color ... colors) throws IllegalArgumentException {
            Preconditions.checkArgument((colors != null ? 1 : 0) != 0, (Object)"Cannot have null colors");
            if (colors.length == 0) {
                return this;
            }
            ImmutableList.Builder<Color> list = this.colors;
            Color[] colorArray = colors;
            int n = colors.length;
            int n2 = 0;
            while (n2 < n) {
                Color color = colorArray[n2];
                Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"Color cannot be null");
                list.add((Object)color);
                ++n2;
            }
            return this;
        }

        @NotNull
        public Builder withColor(@NotNull Iterable<?> colors) throws IllegalArgumentException {
            Preconditions.checkArgument((colors != null ? 1 : 0) != 0, (Object)"Cannot have null colors");
            ImmutableList.Builder<Color> list = this.colors;
            for (Object color : colors) {
                if (!(color instanceof Color)) {
                    throw new IllegalArgumentException(color + " is not a Color in " + colors);
                }
                list.add((Object)((Color)color));
            }
            return this;
        }

        @NotNull
        public Builder withFade(@NotNull Color color) throws IllegalArgumentException {
            Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"Cannot have null color");
            if (this.fadeColors == null) {
                this.fadeColors = ImmutableList.builder();
            }
            this.fadeColors.add((Object)color);
            return this;
        }

        @NotNull
        public Builder withFade(Color ... colors) throws IllegalArgumentException {
            Preconditions.checkArgument((colors != null ? 1 : 0) != 0, (Object)"Cannot have null colors");
            if (colors.length == 0) {
                return this;
            }
            ImmutableList.Builder list = this.fadeColors;
            if (list == null) {
                list = this.fadeColors = ImmutableList.builder();
            }
            Color[] colorArray = colors;
            int n = colors.length;
            int n2 = 0;
            while (n2 < n) {
                Color color = colorArray[n2];
                Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"Color cannot be null");
                list.add((Object)color);
                ++n2;
            }
            return this;
        }

        @NotNull
        public Builder withFade(@NotNull Iterable<?> colors) throws IllegalArgumentException {
            Preconditions.checkArgument((colors != null ? 1 : 0) != 0, (Object)"Cannot have null colors");
            ImmutableList.Builder list = this.fadeColors;
            if (list == null) {
                list = this.fadeColors = ImmutableList.builder();
            }
            for (Object color : colors) {
                if (!(color instanceof Color)) {
                    throw new IllegalArgumentException(color + " is not a Color in " + colors);
                }
                list.add((Object)((Color)color));
            }
            return this;
        }

        @NotNull
        public FireworkEffect build() {
            return new FireworkEffect(this.flicker, this.trail, (ImmutableList<Color>)this.colors.build(), (ImmutableList<Color>)(this.fadeColors == null ? ImmutableList.of() : this.fadeColors.build()), this.type);
        }
    }

    public static enum Type {
        BALL,
        BALL_LARGE,
        STAR,
        BURST,
        CREEPER;

    }
}

