/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.Random;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftProjectile;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class CraftFirework
extends CraftProjectile
implements Firework {
    private final Random random = new Random();
    private final CraftItemStack item;

    public CraftFirework(CraftServer server, FireworkRocketEntity entity) {
        super(server, (Projectile)entity);
        ItemStack item = (ItemStack)this.getHandle().m_20088_().m_135370_(FireworkRocketEntity.f_37019_);
        if (item.m_41619_()) {
            item = new ItemStack((ItemLike)Items.f_42688_);
            this.getHandle().m_20088_().m_135381_(FireworkRocketEntity.f_37019_, (Object)item);
        }
        this.item = CraftItemStack.asCraftMirror(item);
        if (this.item.getType() != Material.FIREWORK_ROCKET) {
            this.item.setType(Material.FIREWORK_ROCKET);
        }
    }

    public FireworkRocketEntity getHandle() {
        return (FireworkRocketEntity)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFirework";
    }

    @Override
    public FireworkMeta getFireworkMeta() {
        return (FireworkMeta)this.item.getItemMeta();
    }

    @Override
    public void setFireworkMeta(FireworkMeta meta) {
        this.item.setItemMeta(meta);
        this.getHandle().f_37023_ = 10 * (1 + meta.getPower()) + this.random.nextInt(6) + this.random.nextInt(7);
        this.getHandle().m_20088_().markDirty(FireworkRocketEntity.f_37019_);
    }

    @Override
    public boolean setAttachedTo(org.bukkit.entity.LivingEntity entity) {
        if (this.isDetonated()) {
            return false;
        }
        this.getHandle().f_37024_ = entity != null ? ((CraftLivingEntity)entity).getHandle() : null;
        return true;
    }

    @Override
    public org.bukkit.entity.LivingEntity getAttachedTo() {
        LivingEntity entity = this.getHandle().f_37024_;
        return entity != null ? (org.bukkit.entity.LivingEntity)((Object)entity.getBukkitEntity()) : null;
    }

    @Override
    public boolean setLife(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"ticks must be greater than or equal to 0");
        if (this.isDetonated()) {
            return false;
        }
        this.getHandle().f_37022_ = ticks;
        return true;
    }

    @Override
    public int getLife() {
        return this.getHandle().f_37022_;
    }

    @Override
    public boolean setMaxLife(int ticks) {
        Preconditions.checkArgument((ticks > 0 ? 1 : 0) != 0, (Object)"ticks must be greater than 0");
        if (this.isDetonated()) {
            return false;
        }
        this.getHandle().f_37023_ = ticks;
        return true;
    }

    @Override
    public int getMaxLife() {
        return this.getHandle().f_37023_;
    }

    @Override
    public void detonate() {
        this.setLife(this.getMaxLife() + 1);
    }

    @Override
    public boolean isDetonated() {
        return this.getHandle().f_37022_ > this.getHandle().f_37023_;
    }

    @Override
    public boolean isShotAtAngle() {
        return this.getHandle().m_37079_();
    }

    @Override
    public void setShotAtAngle(boolean shotAtAngle) {
        this.getHandle().m_20088_().m_135381_(FireworkRocketEntity.f_37021_, (Object)shotAtAngle);
    }
}

