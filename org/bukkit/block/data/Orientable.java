/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block.data;

import java.util.Set;
import org.bukkit.Axis;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface Orientable
extends BlockData {
    @NotNull
    public Axis getAxis();

    public void setAxis(@NotNull Axis var1);

    @NotNull
    public Set<Axis> getAxes();
}

