/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface FallingBlock
extends Entity {
    @Deprecated
    @NotNull
    public Material getMaterial();

    @NotNull
    public BlockData getBlockData();

    public boolean getDropItem();

    public void setDropItem(boolean var1);

    public boolean getCancelDrop();

    public void setCancelDrop(boolean var1);

    public boolean canHurtEntities();

    public void setHurtEntities(boolean var1);

    public float getDamagePerBlock();

    public void setDamagePerBlock(float var1);

    public int getMaxDamage();

    public void setMaxDamage(int var1);
}

