/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

public interface Door
extends Bisected,
Directional,
Openable,
Powerable {
    @NotNull
    public Hinge getHinge();

    public void setHinge(@NotNull Hinge var1);

    public static enum Hinge {
        LEFT,
        RIGHT;

    }
}

