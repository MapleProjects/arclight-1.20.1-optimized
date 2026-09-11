/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.map;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.map.MapCursor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MapCursorCollection {
    private List<MapCursor> cursors = new ArrayList<MapCursor>();

    public int size() {
        return this.cursors.size();
    }

    @NotNull
    public MapCursor getCursor(int index) {
        return this.cursors.get(index);
    }

    public boolean removeCursor(@NotNull MapCursor cursor) {
        return this.cursors.remove(cursor);
    }

    @NotNull
    public MapCursor addCursor(@NotNull MapCursor cursor) {
        this.cursors.add(cursor);
        return cursor;
    }

    @NotNull
    public MapCursor addCursor(int x, int y, byte direction) {
        return this.addCursor(x, y, direction, (byte)0, true);
    }

    @Deprecated
    @NotNull
    public MapCursor addCursor(int x, int y, byte direction, byte type) {
        return this.addCursor(x, y, direction, type, true);
    }

    @Deprecated
    @NotNull
    public MapCursor addCursor(int x, int y, byte direction, byte type, boolean visible) {
        return this.addCursor(new MapCursor((byte)x, (byte)y, direction, type, visible));
    }

    @Deprecated
    @NotNull
    public MapCursor addCursor(int x, int y, byte direction, byte type, boolean visible, @Nullable String caption) {
        return this.addCursor(new MapCursor((byte)x, (byte)y, direction, type, visible, caption));
    }
}

