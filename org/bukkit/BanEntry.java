/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BanEntry<T> {
    @Deprecated
    @NotNull
    public String getTarget();

    @NotNull
    public T getBanTarget();

    @NotNull
    public Date getCreated();

    public void setCreated(@NotNull Date var1);

    @NotNull
    public String getSource();

    public void setSource(@NotNull String var1);

    @Nullable
    public Date getExpiration();

    public void setExpiration(@Nullable Date var1);

    @Nullable
    public String getReason();

    public void setReason(@Nullable String var1);

    public void save();

    public void remove();
}

