/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import org.bukkit.BanEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BanList<T> {
    @Deprecated
    @Nullable
    public BanEntry<T> getBanEntry(@NotNull String var1);

    @Nullable
    public BanEntry<T> getBanEntry(@NotNull T var1);

    @Deprecated
    @Nullable
    public BanEntry<T> addBan(@NotNull String var1, @Nullable String var2, @Nullable Date var3, @Nullable String var4);

    @Nullable
    public BanEntry<T> addBan(@NotNull T var1, @Nullable String var2, @Nullable Date var3, @Nullable String var4);

    @Nullable
    public BanEntry<T> addBan(@NotNull T var1, @Nullable String var2, @Nullable Instant var3, @Nullable String var4);

    @Nullable
    public BanEntry<T> addBan(@NotNull T var1, @Nullable String var2, @Nullable Duration var3, @Nullable String var4);

    @Deprecated
    @NotNull
    public Set<BanEntry> getBanEntries();

    @NotNull
    public Set<BanEntry<T>> getEntries();

    public boolean isBanned(@NotNull T var1);

    @Deprecated
    public boolean isBanned(@NotNull String var1);

    public void pardon(@NotNull T var1);

    @Deprecated
    public void pardon(@NotNull String var1);

    public static enum Type {
        NAME,
        IP,
        PROFILE;

    }
}

