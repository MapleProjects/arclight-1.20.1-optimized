/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public class StringUtil {
    @NotNull
    public static <T extends Collection<? super String>> T copyPartialMatches(@NotNull String token, @NotNull Iterable<String> originals, @NotNull T collection) throws UnsupportedOperationException, IllegalArgumentException {
        Preconditions.checkArgument((token != null ? 1 : 0) != 0, (Object)"Search token cannot be null");
        Preconditions.checkArgument((collection != null ? 1 : 0) != 0, (Object)"Collection cannot be null");
        Preconditions.checkArgument((originals != null ? 1 : 0) != 0, (Object)"Originals cannot be null");
        for (String string : originals) {
            if (!StringUtil.startsWithIgnoreCase(string, token)) continue;
            collection.add((String)string);
        }
        return collection;
    }

    public static boolean startsWithIgnoreCase(@NotNull String string, @NotNull String prefix) throws IllegalArgumentException, NullPointerException {
        Preconditions.checkArgument((string != null ? 1 : 0) != 0, (Object)"Cannot check a null string for a match");
        if (string.length() < prefix.length()) {
            return false;
        }
        return string.regionMatches(true, 0, prefix, 0, prefix.length());
    }
}

