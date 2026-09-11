/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.map;

import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MapFont {
    private final HashMap<Character, CharacterSprite> chars = new HashMap();
    private int height = 0;
    protected boolean malleable = true;

    public void setChar(char ch, @NotNull CharacterSprite sprite) {
        if (!this.malleable) {
            throw new IllegalStateException("this font is not malleable");
        }
        this.chars.put(Character.valueOf(ch), sprite);
        if (sprite.getHeight() > this.height) {
            this.height = sprite.getHeight();
        }
    }

    @Nullable
    public CharacterSprite getChar(char ch) {
        return this.chars.get(Character.valueOf(ch));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getWidth(@NotNull String text) {
        if (!this.isValid(text)) {
            throw new IllegalArgumentException("text contains invalid characters");
        }
        if (text.length() == 0) {
            return 0;
        }
        int result = 0;
        int i = 0;
        while (i < text.length()) {
            char ch = text.charAt(i);
            if (ch == '\u00a7') {
                int j = text.indexOf(59, i);
                if (j < 0) throw new IllegalArgumentException("Text contains unterminated color string");
                i = j;
            } else {
                result += this.chars.get(Character.valueOf(ch)).getWidth();
            }
            ++i;
        }
        return result += text.length() - 1;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isValid(@NotNull String text) {
        int i = 0;
        while (i < text.length()) {
            char ch = text.charAt(i);
            if (ch != '\u00a7' && ch != '\n' && this.chars.get(Character.valueOf(ch)) == null) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static class CharacterSprite {
        private final int width;
        private final int height;
        private final boolean[] data;

        public CharacterSprite(int width, int height, @NotNull boolean[] data) {
            this.width = width;
            this.height = height;
            this.data = data;
            if (data.length != width * height) {
                throw new IllegalArgumentException("size of data does not match dimensions");
            }
        }

        public boolean get(int row, int col) {
            if (row < 0 || col < 0 || row >= this.height || col >= this.width) {
                return false;
            }
            return this.data[row * this.width + col];
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }
    }
}

