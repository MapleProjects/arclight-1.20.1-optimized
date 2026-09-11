/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.structure;

import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.RegionAccessor;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.structure.Palette;
import org.bukkit.util.BlockVector;
import org.jetbrains.annotations.NotNull;

public interface Structure
extends PersistentDataHolder {
    @NotNull
    public BlockVector getSize();

    @NotNull
    public List<Palette> getPalettes();

    public int getPaletteCount();

    @NotNull
    public List<Entity> getEntities();

    public int getEntityCount();

    public void place(@NotNull Location var1, boolean var2, @NotNull StructureRotation var3, @NotNull Mirror var4, int var5, float var6, @NotNull Random var7);

    public void place(@NotNull RegionAccessor var1, @NotNull BlockVector var2, boolean var3, @NotNull StructureRotation var4, @NotNull Mirror var5, int var6, float var7, @NotNull Random var8);

    public void fill(@NotNull Location var1, @NotNull Location var2, boolean var3);

    public void fill(@NotNull Location var1, @NotNull BlockVector var2, boolean var3);
}

