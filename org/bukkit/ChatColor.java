/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Maps
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.regex.Pattern;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ChatColor {
    BLACK('0', 0){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.BLACK;
        }
    }
    ,
    DARK_BLUE('1', 1){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.DARK_BLUE;
        }
    }
    ,
    DARK_GREEN('2', 2){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.DARK_GREEN;
        }
    }
    ,
    DARK_AQUA('3', 3){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.DARK_AQUA;
        }
    }
    ,
    DARK_RED('4', 4){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.DARK_RED;
        }
    }
    ,
    DARK_PURPLE('5', 5){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.DARK_PURPLE;
        }
    }
    ,
    GOLD('6', 6){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.GOLD;
        }
    }
    ,
    GRAY('7', 7){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.GRAY;
        }
    }
    ,
    DARK_GRAY('8', 8){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.DARK_GRAY;
        }
    }
    ,
    BLUE('9', 9){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.BLUE;
        }
    }
    ,
    GREEN('a', 10){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.GREEN;
        }
    }
    ,
    AQUA('b', 11){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.AQUA;
        }
    }
    ,
    RED('c', 12){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.RED;
        }
    }
    ,
    LIGHT_PURPLE('d', 13){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.LIGHT_PURPLE;
        }
    }
    ,
    YELLOW('e', 14){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.YELLOW;
        }
    }
    ,
    WHITE('f', 15){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.WHITE;
        }
    }
    ,
    MAGIC('k', 16, true){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.MAGIC;
        }
    }
    ,
    BOLD('l', 17, true){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.BOLD;
        }
    }
    ,
    STRIKETHROUGH('m', 18, true){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.STRIKETHROUGH;
        }
    }
    ,
    UNDERLINE('n', 19, true){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.UNDERLINE;
        }
    }
    ,
    ITALIC('o', 20, true){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.ITALIC;
        }
    }
    ,
    RESET('r', 21){

        @Override
        @NotNull
        public net.md_5.bungee.api.ChatColor asBungee() {
            return net.md_5.bungee.api.ChatColor.RESET;
        }
    };

    public static final char COLOR_CHAR = '\u00a7';
    private static final Pattern STRIP_COLOR_PATTERN;
    private final int intCode;
    private final char code;
    private final boolean isFormat;
    private final String toString;
    private static final Map<Integer, ChatColor> BY_ID;
    private static final Map<Character, ChatColor> BY_CHAR;

    static {
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-ORX]");
        BY_ID = Maps.newHashMap();
        BY_CHAR = Maps.newHashMap();
        ChatColor[] chatColorArray = ChatColor.values();
        int n = chatColorArray.length;
        int n2 = 0;
        while (n2 < n) {
            ChatColor color = chatColorArray[n2];
            BY_ID.put(color.intCode, color);
            BY_CHAR.put(Character.valueOf(color.code), color);
            ++n2;
        }
    }

    private ChatColor(char code, int intCode) {
        this(code, intCode, false);
    }

    private ChatColor(char code, int intCode, boolean isFormat) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = isFormat;
        this.toString = new String(new char[]{'\u00a7', code});
    }

    @NotNull
    public net.md_5.bungee.api.ChatColor asBungee() {
        return net.md_5.bungee.api.ChatColor.RESET;
    }

    public char getChar() {
        return this.code;
    }

    @NotNull
    public String toString() {
        return this.toString;
    }

    public boolean isFormat() {
        return this.isFormat;
    }

    public boolean isColor() {
        return !this.isFormat && this != RESET;
    }

    @Nullable
    public static ChatColor getByChar(char code) {
        return BY_CHAR.get(Character.valueOf(code));
    }

    @Nullable
    public static ChatColor getByChar(@NotNull String code) {
        Preconditions.checkArgument((code != null ? 1 : 0) != 0, (Object)"Code cannot be null");
        Preconditions.checkArgument((code.length() > 0 ? 1 : 0) != 0, (Object)"Code must have at least one char");
        return BY_CHAR.get(Character.valueOf(code.charAt(0)));
    }

    @Contract(value="!null -> !null; null -> null")
    @Nullable
    public static String stripColor(@Nullable String input) {
        if (input == null) {
            return null;
        }
        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    @NotNull
    public static String translateAlternateColorCodes(char altColorChar, @NotNull String textToTranslate) {
        Preconditions.checkArgument((textToTranslate != null ? 1 : 0) != 0, (Object)"Cannot translate null text");
        char[] b = textToTranslate.toCharArray();
        int i = 0;
        while (i < b.length - 1) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
                b[i] = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
            ++i;
        }
        return new String(b);
    }

    @NotNull
    public static String getLastColors(@NotNull String input) {
        Preconditions.checkArgument((input != null ? 1 : 0) != 0, (Object)"Cannot get last colors from null text");
        String result = "";
        int length = input.length();
        int index = length - 1;
        while (index > -1) {
            char section = input.charAt(index);
            if (section == '\u00a7' && index < length - 1) {
                String hexColor = ChatColor.getHexColor(input, index);
                if (hexColor != null) {
                    result = String.valueOf(hexColor) + result;
                    break;
                }
                char c = input.charAt(index + 1);
                ChatColor color = ChatColor.getByChar(c);
                if (color != null) {
                    result = String.valueOf(color.toString()) + result;
                    if (color.isColor() || color.equals((Object)RESET)) break;
                }
            }
            --index;
        }
        return result;
    }

    @Nullable
    private static String getHexColor(@NotNull String input, int index) {
        if (index < 12) {
            return null;
        }
        if (input.charAt(index - 11) != 'x' || input.charAt(index - 12) != '\u00a7') {
            return null;
        }
        int i = index - 10;
        while (i <= index) {
            if (input.charAt(i) != '\u00a7') {
                return null;
            }
            i += 2;
        }
        i = index - 9;
        while (i <= index + 1) {
            char toCheck = input.charAt(i);
            if (toCheck < '0' || toCheck > 'f') {
                return null;
            }
            if (toCheck > '9' && toCheck < 'A') {
                return null;
            }
            if (toCheck > 'F' && toCheck < 'a') {
                return null;
            }
            i += 2;
        }
        return input.substring(index - 12, index + 2);
    }

    /* synthetic */ ChatColor(String string, int n, char c, int n2, ChatColor chatColor) {
        this(c, n2);
    }

    /* synthetic */ ChatColor(String string, int n, char c, int n2, boolean bl, ChatColor chatColor) {
        this(c, n2, bl);
    }
}

