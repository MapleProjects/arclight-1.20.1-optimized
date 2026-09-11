/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum DyeColor {
    WHITE(0, 15, Color.fromRGB(0xF9FFFE), Color.fromRGB(0xF0F0F0)),
    ORANGE(1, 14, Color.fromRGB(16351261), Color.fromRGB(15435844)),
    MAGENTA(2, 13, Color.fromRGB(13061821), Color.fromRGB(12801229)),
    LIGHT_BLUE(3, 12, Color.fromRGB(3847130), Color.fromRGB(6719955)),
    YELLOW(4, 11, Color.fromRGB(16701501), Color.fromRGB(14602026)),
    LIME(5, 10, Color.fromRGB(8439583), Color.fromRGB(4312372)),
    PINK(6, 9, Color.fromRGB(15961002), Color.fromRGB(14188952)),
    GRAY(7, 8, Color.fromRGB(4673362), Color.fromRGB(0x434343)),
    LIGHT_GRAY(8, 7, Color.fromRGB(0x9D9D97), Color.fromRGB(0xABABAB)),
    CYAN(9, 6, Color.fromRGB(1481884), Color.fromRGB(2651799)),
    PURPLE(10, 5, Color.fromRGB(8991416), Color.fromRGB(8073150)),
    BLUE(11, 4, Color.fromRGB(3949738), Color.fromRGB(2437522)),
    BROWN(12, 3, Color.fromRGB(8606770), Color.fromRGB(5320730)),
    GREEN(13, 2, Color.fromRGB(6192150), Color.fromRGB(3887386)),
    RED(14, 1, Color.fromRGB(11546150), Color.fromRGB(11743532)),
    BLACK(15, 0, Color.fromRGB(0x1D1D21), Color.fromRGB(0x1E1B1B));

    private final byte woolData;
    private final byte dyeData;
    private final Color color;
    private final Color firework;
    private static final DyeColor[] BY_WOOL_DATA;
    private static final DyeColor[] BY_DYE_DATA;
    private static final Map<Color, DyeColor> BY_COLOR;
    private static final Map<Color, DyeColor> BY_FIREWORK;

    static {
        BY_WOOL_DATA = DyeColor.values();
        BY_DYE_DATA = DyeColor.values();
        ImmutableMap.Builder byColor = ImmutableMap.builder();
        ImmutableMap.Builder byFirework = ImmutableMap.builder();
        DyeColor[] dyeColorArray = DyeColor.values();
        int n = dyeColorArray.length;
        int n2 = 0;
        while (n2 < n) {
            DyeColor color;
            DyeColor.BY_WOOL_DATA[color.woolData & 0xFF] = color = dyeColorArray[n2];
            DyeColor.BY_DYE_DATA[color.dyeData & 0xFF] = color;
            byColor.put((Object)color.getColor(), (Object)color);
            byFirework.put((Object)color.getFireworkColor(), (Object)color);
            ++n2;
        }
        BY_COLOR = byColor.build();
        BY_FIREWORK = byFirework.build();
    }

    private DyeColor(int woolData, int dyeData, Color color, Color firework) {
        this.woolData = (byte)woolData;
        this.dyeData = (byte)dyeData;
        this.color = color;
        this.firework = firework;
    }

    @Deprecated
    public byte getWoolData() {
        return this.woolData;
    }

    @Deprecated
    public byte getDyeData() {
        return this.dyeData;
    }

    @NotNull
    public Color getColor() {
        return this.color;
    }

    @NotNull
    public Color getFireworkColor() {
        return this.firework;
    }

    @Deprecated
    @Nullable
    public static DyeColor getByWoolData(byte data) {
        int i = 0xFF & data;
        if (i >= BY_WOOL_DATA.length) {
            return null;
        }
        return BY_WOOL_DATA[i];
    }

    @Deprecated
    @Nullable
    public static DyeColor getByDyeData(byte data) {
        int i = 0xFF & data;
        if (i >= BY_DYE_DATA.length) {
            return null;
        }
        return BY_DYE_DATA[i];
    }

    @Nullable
    public static DyeColor getByColor(@NotNull Color color) {
        return BY_COLOR.get(color);
    }

    @Nullable
    public static DyeColor getByFireworkColor(@NotNull Color color) {
        return BY_FIREWORK.get(color);
    }

    @Deprecated
    @NotNull
    public static DyeColor legacyValueOf(@Nullable String name) {
        return "SILVER".equals(name) ? LIGHT_GRAY : DyeColor.valueOf(name);
    }
}

