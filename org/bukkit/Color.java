/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import org.bukkit.DyeColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SerializableAs(value="Color")
public final class Color
implements ConfigurationSerializable {
    private static final int BIT_MASK = 255;
    private static final int DEFAULT_ALPHA = 255;
    public static final Color WHITE = Color.fromRGB(0xFFFFFF);
    public static final Color SILVER = Color.fromRGB(0xC0C0C0);
    public static final Color GRAY = Color.fromRGB(0x808080);
    public static final Color BLACK = Color.fromRGB(0);
    public static final Color RED = Color.fromRGB(0xFF0000);
    public static final Color MAROON = Color.fromRGB(0x800000);
    public static final Color YELLOW = Color.fromRGB(0xFFFF00);
    public static final Color OLIVE = Color.fromRGB(0x808000);
    public static final Color LIME = Color.fromRGB(65280);
    public static final Color GREEN = Color.fromRGB(32768);
    public static final Color AQUA = Color.fromRGB(65535);
    public static final Color TEAL = Color.fromRGB(32896);
    public static final Color BLUE = Color.fromRGB(255);
    public static final Color NAVY = Color.fromRGB(128);
    public static final Color FUCHSIA = Color.fromRGB(0xFF00FF);
    public static final Color PURPLE = Color.fromRGB(0x800080);
    public static final Color ORANGE = Color.fromRGB(16753920);
    private final byte alpha;
    private final byte red;
    private final byte green;
    private final byte blue;

    @NotNull
    public static Color fromARGB(int alpha, int red, int green, int blue) throws IllegalArgumentException {
        return new Color(alpha, red, green, blue);
    }

    @NotNull
    public static Color fromRGB(int red, int green, int blue) throws IllegalArgumentException {
        return new Color(255, red, green, blue);
    }

    @NotNull
    public static Color fromBGR(int blue, int green, int red) throws IllegalArgumentException {
        return new Color(255, red, green, blue);
    }

    @NotNull
    public static Color fromRGB(int rgb) throws IllegalArgumentException {
        Preconditions.checkArgument((rgb >> 24 == 0 ? 1 : 0) != 0, (String)"Extraneous data in: %s", (int)rgb);
        return Color.fromRGB(rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb & 0xFF);
    }

    @NotNull
    public static Color fromARGB(int argb) {
        return Color.fromARGB(argb >> 24 & 0xFF, argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);
    }

    @NotNull
    public static Color fromBGR(int bgr) throws IllegalArgumentException {
        Preconditions.checkArgument((bgr >> 24 == 0 ? 1 : 0) != 0, (String)"Extrenuous data in: %s", (int)bgr);
        return Color.fromBGR(bgr >> 16 & 0xFF, bgr >> 8 & 0xFF, bgr & 0xFF);
    }

    private Color(int red, int green, int blue) {
        this(255, red, green, blue);
    }

    private Color(int alpha, int red, int green, int blue) {
        Preconditions.checkArgument((alpha >= 0 && alpha <= 255 ? 1 : 0) != 0, (String)"Alpha[%s] is not between 0-255", (int)alpha);
        Preconditions.checkArgument((red >= 0 && red <= 255 ? 1 : 0) != 0, (String)"Red[%s] is not between 0-255", (int)red);
        Preconditions.checkArgument((green >= 0 && green <= 255 ? 1 : 0) != 0, (String)"Green[%s] is not between 0-255", (int)green);
        Preconditions.checkArgument((blue >= 0 && blue <= 255 ? 1 : 0) != 0, (String)"Blue[%s] is not between 0-255", (int)blue);
        this.alpha = (byte)alpha;
        this.red = (byte)red;
        this.green = (byte)green;
        this.blue = (byte)blue;
    }

    public int getAlpha() {
        return 0xFF & this.alpha;
    }

    @NotNull
    public Color setAlpha(int alpha) {
        return Color.fromARGB(alpha, this.getRed(), this.getGreen(), this.getBlue());
    }

    public int getRed() {
        return 0xFF & this.red;
    }

    @NotNull
    public Color setRed(int red) {
        return Color.fromARGB(this.getAlpha(), red, this.getGreen(), this.getBlue());
    }

    public int getGreen() {
        return 0xFF & this.green;
    }

    @NotNull
    public Color setGreen(int green) {
        return Color.fromARGB(this.getAlpha(), this.getRed(), green, this.getBlue());
    }

    public int getBlue() {
        return 0xFF & this.blue;
    }

    @NotNull
    public Color setBlue(int blue) {
        return Color.fromARGB(this.getAlpha(), this.getRed(), this.getGreen(), blue);
    }

    public int asRGB() {
        return this.getRed() << 16 | this.getGreen() << 8 | this.getBlue();
    }

    public int asARGB() {
        return this.getAlpha() << 24 | this.getRed() << 16 | this.getGreen() << 8 | this.getBlue();
    }

    public int asBGR() {
        return this.getBlue() << 16 | this.getGreen() << 8 | this.getRed();
    }

    @NotNull
    public Color mixDyes(DyeColor ... colors) {
        Preconditions.checkArgument((colors != null && Arrays.stream(colors).allMatch(Objects::nonNull) ? 1 : 0) != 0, (Object)"DyeColor cannot be null or contain null values");
        Color[] toPass = new Color[colors.length];
        int i = 0;
        while (i < colors.length) {
            toPass[i] = colors[i].getColor();
            ++i;
        }
        return this.mixColors(toPass);
    }

    @NotNull
    public Color mixColors(Color ... colors) {
        Preconditions.checkArgument((colors != null && Arrays.stream(colors).allMatch(Objects::nonNull) ? 1 : 0) != 0, (Object)"Colors cannot be null");
        int totalRed = this.getRed();
        int totalGreen = this.getGreen();
        int totalBlue = this.getBlue();
        int totalMax = Math.max(Math.max(totalRed, totalGreen), totalBlue);
        Color[] colorArray = colors;
        int n = colors.length;
        int n2 = 0;
        while (n2 < n) {
            Color color = colorArray[n2];
            totalRed += color.getRed();
            totalGreen += color.getGreen();
            totalBlue += color.getBlue();
            totalMax += Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue());
            ++n2;
        }
        float averageRed = totalRed / (colors.length + 1);
        float averageGreen = totalGreen / (colors.length + 1);
        float averageBlue = totalBlue / (colors.length + 1);
        float averageMax = totalMax / (colors.length + 1);
        float maximumOfAverages = Math.max(Math.max(averageRed, averageGreen), averageBlue);
        float gainFactor = averageMax / maximumOfAverages;
        return Color.fromRGB((int)(averageRed * gainFactor), (int)(averageGreen * gainFactor), (int)(averageBlue * gainFactor));
    }

    public boolean equals(Object o) {
        if (!(o instanceof Color)) {
            return false;
        }
        Color that = (Color)o;
        return this.alpha == that.alpha && this.blue == that.blue && this.green == that.green && this.red == that.red;
    }

    public int hashCode() {
        return this.asARGB() ^ Color.class.hashCode();
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        return ImmutableMap.of((Object)"ALPHA", (Object)this.getAlpha(), (Object)"RED", (Object)this.getRed(), (Object)"BLUE", (Object)this.getBlue(), (Object)"GREEN", (Object)this.getGreen());
    }

    @NotNull
    public static Color deserialize(@NotNull Map<String, Object> map) {
        return Color.fromARGB(Color.asInt("ALPHA", map, 255), Color.asInt("RED", map), Color.asInt("GREEN", map), Color.asInt("BLUE", map));
    }

    private static int asInt(@NotNull String string, @NotNull Map<String, Object> map) {
        return Color.asInt(string, map, null);
    }

    private static int asInt(@NotNull String string, @NotNull Map<String, Object> map, @Nullable Object defaultValue) {
        Object value = map.getOrDefault(string, defaultValue);
        if (value == null) {
            throw new IllegalArgumentException(String.valueOf(string) + " not in map " + map);
        }
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException(String.valueOf(string) + '(' + value + ") is not a number");
        }
        return ((Number)value).intValue();
    }

    public String toString() {
        return "Color:[argb0x" + Integer.toHexString(this.asARGB()).toUpperCase() + "]";
    }
}

