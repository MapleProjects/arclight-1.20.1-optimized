/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NumberConversions {
    private NumberConversions() {
    }

    public static int floor(double num) {
        int floor = (int)num;
        return (double)floor == num ? floor : floor - (int)(Double.doubleToRawLongBits(num) >>> 63);
    }

    public static int ceil(double num) {
        int floor = (int)num;
        return (double)floor == num ? floor : floor + (int)((Double.doubleToRawLongBits(num) ^ 0xFFFFFFFFFFFFFFFFL) >>> 63);
    }

    public static int round(double num) {
        return NumberConversions.floor(num + 0.5);
    }

    public static double square(double num) {
        return num * num;
    }

    public static int toInt(@Nullable Object object) {
        if (object instanceof Number) {
            return ((Number)object).intValue();
        }
        try {
            return Integer.parseInt(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return 0;
    }

    public static float toFloat(@Nullable Object object) {
        if (object instanceof Number) {
            return ((Number)object).floatValue();
        }
        try {
            return Float.parseFloat(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return 0.0f;
    }

    public static double toDouble(@Nullable Object object) {
        if (object instanceof Number) {
            return ((Number)object).doubleValue();
        }
        try {
            return Double.parseDouble(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return 0.0;
    }

    public static long toLong(@Nullable Object object) {
        if (object instanceof Number) {
            return ((Number)object).longValue();
        }
        try {
            return Long.parseLong(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return 0L;
    }

    public static short toShort(@Nullable Object object) {
        if (object instanceof Number) {
            return ((Number)object).shortValue();
        }
        try {
            return Short.parseShort(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return 0;
    }

    public static byte toByte(@Nullable Object object) {
        if (object instanceof Number) {
            return ((Number)object).byteValue();
        }
        try {
            return Byte.parseByte(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return 0;
    }

    public static boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    public static boolean isFinite(float f) {
        return Math.abs(f) <= Float.MAX_VALUE;
    }

    public static void checkFinite(double d, @NotNull String message) {
        if (!NumberConversions.isFinite(d)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkFinite(float d, @NotNull String message) {
        if (!NumberConversions.isFinite(d)) {
            throw new IllegalArgumentException(message);
        }
    }
}

