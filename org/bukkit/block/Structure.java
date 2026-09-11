/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.block;

import org.bukkit.block.TileState;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.block.structure.UsageMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockVector;
import org.jetbrains.annotations.NotNull;

public interface Structure
extends TileState {
    @NotNull
    public String getStructureName();

    public void setStructureName(@NotNull String var1);

    @NotNull
    public String getAuthor();

    public void setAuthor(@NotNull String var1);

    public void setAuthor(@NotNull LivingEntity var1);

    @NotNull
    public BlockVector getRelativePosition();

    public void setRelativePosition(@NotNull BlockVector var1);

    @NotNull
    public BlockVector getStructureSize();

    public void setStructureSize(@NotNull BlockVector var1);

    public void setMirror(@NotNull Mirror var1);

    @NotNull
    public Mirror getMirror();

    public void setRotation(@NotNull StructureRotation var1);

    @NotNull
    public StructureRotation getRotation();

    public void setUsageMode(@NotNull UsageMode var1);

    @NotNull
    public UsageMode getUsageMode();

    public void setIgnoreEntities(boolean var1);

    public boolean isIgnoreEntities();

    public void setShowAir(boolean var1);

    public boolean isShowAir();

    public void setBoundingBoxVisible(boolean var1);

    public boolean isBoundingBoxVisible();

    public void setIntegrity(float var1);

    public float getIntegrity();

    public void setSeed(long var1);

    public long getSeed();

    public void setMetadata(@NotNull String var1);

    @NotNull
    public String getMetadata();
}

