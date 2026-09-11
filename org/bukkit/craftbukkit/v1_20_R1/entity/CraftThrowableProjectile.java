/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.Projectile
 *  net.minecraft.world.entity.projectile.ThrowableItemProjectile
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftProjectile;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.ThrowableProjectile;

public abstract class CraftThrowableProjectile
extends CraftProjectile
implements ThrowableProjectile {
    public CraftThrowableProjectile(CraftServer server, ThrowableItemProjectile entity) {
        super(server, (Projectile)entity);
    }

    @Override
    public org.bukkit.inventory.ItemStack getItem() {
        if (this.getHandle().m_37454_().m_41619_()) {
            return CraftItemStack.asBukkitCopy(new ItemStack((ItemLike)this.getHandle().getDefaultItemPublic()));
        }
        return CraftItemStack.asBukkitCopy(this.getHandle().m_37454_());
    }

    @Override
    public void setItem(org.bukkit.inventory.ItemStack item) {
        this.getHandle().m_37446_(CraftItemStack.asNMSCopy(item));
    }

    public ThrowableItemProjectile getHandle() {
        return (ThrowableItemProjectile)this.entity;
    }
}

