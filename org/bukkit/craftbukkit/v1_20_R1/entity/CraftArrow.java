/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.AbstractProjectile;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.projectiles.ProjectileSource;

public class CraftArrow
extends AbstractProjectile
implements org.bukkit.entity.AbstractArrow {
    public CraftArrow(CraftServer server, AbstractArrow entity) {
        super(server, (net.minecraft.world.entity.Entity)entity);
    }

    @Override
    public void setKnockbackStrength(int knockbackStrength) {
        Preconditions.checkArgument((knockbackStrength >= 0 ? 1 : 0) != 0, (String)"Knockback value (%s) cannot be negative", (int)knockbackStrength);
        this.getHandle().m_36735_(knockbackStrength);
    }

    @Override
    public int getKnockbackStrength() {
        return this.getHandle().f_36699_;
    }

    @Override
    public double getDamage() {
        return this.getHandle().m_36789_();
    }

    @Override
    public void setDamage(double damage) {
        Preconditions.checkArgument((damage >= 0.0 ? 1 : 0) != 0, (String)"Damage value (%s) must be positive", (Object)damage);
        this.getHandle().m_36781_(damage);
    }

    @Override
    public int getPierceLevel() {
        return this.getHandle().m_36796_();
    }

    @Override
    public void setPierceLevel(int pierceLevel) {
        Preconditions.checkArgument((pierceLevel >= 0 && pierceLevel <= 127 ? 1 : 0) != 0, (String)"Pierce level (%s) out of range, expected 0 < level < 127", (int)pierceLevel);
        this.getHandle().m_36767_((byte)pierceLevel);
    }

    @Override
    public boolean isCritical() {
        return this.getHandle().m_36792_();
    }

    @Override
    public void setCritical(boolean critical) {
        this.getHandle().m_36762_(critical);
    }

    @Override
    public ProjectileSource getShooter() {
        return this.getHandle().projectileSource;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof Entity) {
            this.getHandle().m_5602_(((CraftEntity)((Object)shooter)).getHandle());
        } else {
            this.getHandle().m_5602_(null);
        }
        this.getHandle().projectileSource = shooter;
    }

    @Override
    public boolean isInBlock() {
        return this.getHandle().f_36703_;
    }

    @Override
    public Block getAttachedBlock() {
        if (!this.isInBlock()) {
            return null;
        }
        BlockPos pos = this.getHandle().m_20183_();
        return this.getWorld().getBlockAt(pos.m_123341_(), pos.m_123342_(), pos.m_123343_());
    }

    @Override
    public AbstractArrow.PickupStatus getPickupStatus() {
        return AbstractArrow.PickupStatus.values()[this.getHandle().f_36705_.ordinal()];
    }

    @Override
    public void setPickupStatus(AbstractArrow.PickupStatus status) {
        Preconditions.checkArgument((status != null ? 1 : 0) != 0, (Object)"PickupStatus cannot be null");
        this.getHandle().f_36705_ = AbstractArrow.Pickup.m_36808_((int)status.ordinal());
    }

    @Override
    public void setTicksLived(int value) {
        super.setTicksLived(value);
        this.getHandle().f_36697_ = value;
    }

    @Override
    public boolean isShotFromCrossbow() {
        return this.getHandle().m_36795_();
    }

    @Override
    public void setShotFromCrossbow(boolean shotFromCrossbow) {
        this.getHandle().m_36793_(shotFromCrossbow);
    }

    public AbstractArrow getHandle() {
        return (AbstractArrow)this.entity;
    }

    @Override
    public String toString() {
        return "CraftArrow";
    }
}

