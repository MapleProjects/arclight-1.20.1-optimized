/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

public interface Comparator
extends Directional,
Powerable {
    @NotNull
    public Mode getMode();

    public void setMode(@NotNull Mode var1);

    public static enum Mode {
        COMPARE,
        SUBTRACT;

    }
}

