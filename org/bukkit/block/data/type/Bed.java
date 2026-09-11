/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;

public interface Bed
extends Directional {
    @NotNull
    public Part getPart();

    public void setPart(@NotNull Part var1);

    public boolean isOccupied();

    public static enum Part {
        HEAD,
        FOOT;

    }
}

