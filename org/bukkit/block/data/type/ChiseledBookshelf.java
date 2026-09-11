/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;

public interface ChiseledBookshelf
extends Directional {
    public boolean isSlotOccupied(int var1);

    public void setSlotOccupied(int var1, boolean var2);

    @NotNull
    public Set<Integer> getOccupiedSlots();

    public int getMaximumOccupiedSlots();
}

