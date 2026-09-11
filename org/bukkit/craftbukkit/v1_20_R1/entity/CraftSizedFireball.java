/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.Fireball
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFireball;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.SizedFireball;
import org.bukkit.inventory.ItemStack;

public class CraftSizedFireball
extends CraftFireball
implements SizedFireball {
    public CraftSizedFireball(CraftServer server, Fireball entity) {
        super(server, (AbstractHurtingProjectile)entity);
    }

    @Override
    public ItemStack getDisplayItem() {
        if (this.getHandle().m_37018_().m_41619_()) {
            return new ItemStack(Material.FIRE_CHARGE);
        }
        return CraftItemStack.asBukkitCopy(this.getHandle().m_37018_());
    }

    @Override
    public void setDisplayItem(ItemStack item) {
        this.getHandle().m_37010_(CraftItemStack.asNMSCopy(item));
    }

    public Fireball getHandle() {
        return (Fireball)this.entity;
    }
}

