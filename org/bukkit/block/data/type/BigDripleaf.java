/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.type.Dripleaf;
import org.jetbrains.annotations.NotNull;

public interface BigDripleaf
extends Dripleaf {
    @NotNull
    public Tilt getTilt();

    public void setTilt(@NotNull Tilt var1);

    public static enum Tilt {
        NONE,
        UNSTABLE,
        PARTIAL,
        FULL;

    }
}

