/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Sapling;
import org.jetbrains.annotations.NotNull;

public interface Bamboo
extends Ageable,
Sapling {
    @NotNull
    public Leaves getLeaves();

    public void setLeaves(@NotNull Leaves var1);

    public static enum Leaves {
        NONE,
        SMALL,
        LARGE;

    }
}

