/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;

public interface TechnicalPiston
extends Directional {
    @NotNull
    public Type getType();

    public void setType(@NotNull Type var1);

    public static enum Type {
        NORMAL,
        STICKY;

    }
}

