/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.FallingBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.FallingBlock;

public class CraftFallingBlock
extends CraftEntity
implements FallingBlock {
    public CraftFallingBlock(CraftServer server, FallingBlockEntity entity) {
        super(server, (Entity)entity);
    }

    public FallingBlockEntity getHandle() {
        return (FallingBlockEntity)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFallingBlock";
    }

    @Override
    public Material getMaterial() {
        return this.getBlockData().getMaterial();
    }

    @Override
    public BlockData getBlockData() {
        return CraftBlockData.fromData(this.getHandle().m_31980_());
    }

    @Override
    public boolean getDropItem() {
        return this.getHandle().f_31943_;
    }

    @Override
    public void setDropItem(boolean drop) {
        this.getHandle().f_31943_ = drop;
    }

    @Override
    public boolean getCancelDrop() {
        return this.getHandle().f_31947_;
    }

    @Override
    public void setCancelDrop(boolean cancelDrop) {
        this.getHandle().f_31947_ = cancelDrop;
    }

    @Override
    public boolean canHurtEntities() {
        return this.getHandle().f_31939_;
    }

    @Override
    public void setHurtEntities(boolean hurtEntities) {
        this.getHandle().f_31939_ = hurtEntities;
    }

    @Override
    public void setTicksLived(int value) {
        super.setTicksLived(value);
        this.getHandle().f_31942_ = value;
    }

    @Override
    public float getDamagePerBlock() {
        return this.getHandle().f_149641_;
    }

    @Override
    public void setDamagePerBlock(float damage) {
        Preconditions.checkArgument(((double)damage >= 0.0 ? 1 : 0) != 0, (String)"damage must be >= 0.0, given %s", (Object)Float.valueOf(damage));
        this.getHandle().f_149641_ = damage;
        if ((double)damage > 0.0) {
            this.setHurtEntities(true);
        }
    }

    @Override
    public int getMaxDamage() {
        return this.getHandle().f_31940_;
    }

    @Override
    public void setMaxDamage(int damage) {
        Preconditions.checkArgument((damage >= 0 ? 1 : 0) != 0, (String)"damage must be >= 0, given %s", (int)damage);
        this.getHandle().f_31940_ = damage;
        if (damage > 0) {
            this.setHurtEntities(true);
        }
    }
}

