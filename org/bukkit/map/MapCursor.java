/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MapCursor {
    private byte x;
    private byte y;
    private byte direction;
    private byte type;
    private boolean visible;
    private String caption;

    @Deprecated
    public MapCursor(byte x, byte y, byte direction, byte type, boolean visible) {
        this(x, y, direction, type, visible, null);
    }

    public MapCursor(byte x, byte y, byte direction, @NotNull Type type, boolean visible) {
        this(x, y, direction, type, visible, null);
    }

    @Deprecated
    public MapCursor(byte x, byte y, byte direction, byte type, boolean visible, @Nullable String caption) {
        this.x = x;
        this.y = y;
        this.setDirection(direction);
        this.setRawType(type);
        this.visible = visible;
        this.caption = caption;
    }

    public MapCursor(byte x, byte y, byte direction, @NotNull Type type, boolean visible, @Nullable String caption) {
        this.x = x;
        this.y = y;
        this.setDirection(direction);
        this.setType(type);
        this.visible = visible;
        this.caption = caption;
    }

    public byte getX() {
        return this.x;
    }

    public byte getY() {
        return this.y;
    }

    public byte getDirection() {
        return this.direction;
    }

    @NotNull
    public Type getType() {
        return Type.byValue(this.type);
    }

    @Deprecated
    public byte getRawType() {
        return this.type;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public void setDirection(byte direction) {
        if (direction < 0 || direction > 15) {
            throw new IllegalArgumentException("Direction must be in the range 0-15");
        }
        this.direction = direction;
    }

    public void setType(@NotNull Type type) {
        this.setRawType(type.value);
    }

    @Deprecated
    public void setRawType(byte type) {
        if (type < 0 || type > 26) {
            throw new IllegalArgumentException("Type must be in the range 0-26");
        }
        this.type = type;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Nullable
    public String getCaption() {
        return this.caption;
    }

    public void setCaption(@Nullable String caption) {
        this.caption = caption;
    }

    public static enum Type {
        WHITE_POINTER(0),
        GREEN_POINTER(1),
        RED_POINTER(2),
        BLUE_POINTER(3),
        WHITE_CROSS(4),
        RED_MARKER(5),
        WHITE_CIRCLE(6),
        SMALL_WHITE_CIRCLE(7),
        MANSION(8),
        TEMPLE(9),
        BANNER_WHITE(10),
        BANNER_ORANGE(11),
        BANNER_MAGENTA(12),
        BANNER_LIGHT_BLUE(13),
        BANNER_YELLOW(14),
        BANNER_LIME(15),
        BANNER_PINK(16),
        BANNER_GRAY(17),
        BANNER_LIGHT_GRAY(18),
        BANNER_CYAN(19),
        BANNER_PURPLE(20),
        BANNER_BLUE(21),
        BANNER_BROWN(22),
        BANNER_GREEN(23),
        BANNER_RED(24),
        BANNER_BLACK(25),
        RED_X(26);

        private byte value;

        private Type(int value) {
            this.value = (byte)value;
        }

        @Deprecated
        public byte getValue() {
            return this.value;
        }

        @Deprecated
        @Nullable
        public static Type byValue(byte value) {
            Type[] typeArray = Type.values();
            int n = typeArray.length;
            int n2 = 0;
            while (n2 < n) {
                Type t = typeArray[n2];
                if (t.value == value) {
                    return t;
                }
                ++n2;
            }
            return null;
        }
    }
}

