/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity.minecart;

import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandMinecart
extends Minecart {
    @NotNull
    public String getCommand();

    public void setCommand(@Nullable String var1);

    public void setName(@Nullable String var1);
}

