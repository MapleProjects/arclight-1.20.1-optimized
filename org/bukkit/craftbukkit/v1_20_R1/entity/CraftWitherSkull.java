/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.WitherSkull
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.WitherSkull;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFireball;

public class CraftWitherSkull
extends CraftFireball
implements org.bukkit.entity.WitherSkull {
    public CraftWitherSkull(CraftServer server, WitherSkull entity) {
        super(server, (AbstractHurtingProjectile)entity);
    }

    @Override
    public void setCharged(boolean charged) {
        this.getHandle().m_37629_(charged);
    }

    @Override
    public boolean isCharged() {
        return this.getHandle().m_37635_();
    }

    public WitherSkull getHandle() {
        return (WitherSkull)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWitherSkull";
    }
}

