/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface BrewingStand
extends BlockData {
    public boolean hasBottle(int var1);

    public void setBottle(int var1, boolean var2);

    @NotNull
    public Set<Integer> getBottles();

    public int getMaximumBottles();
}

