/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.generator;

import java.util.UUID;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public interface WorldInfo {
    @NotNull
    public String getName();

    @NotNull
    public UUID getUID();

    @NotNull
    public World.Environment getEnvironment();

    public long getSeed();

    public int getMinHeight();

    public int getMaxHeight();
}

