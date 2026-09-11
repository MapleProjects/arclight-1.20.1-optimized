/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.boss.EnderDragonPart
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;

public class CraftComplexPart
extends CraftEntity
implements ComplexEntityPart {
    public CraftComplexPart(CraftServer server, EnderDragonPart entity) {
        super(server, (Entity)entity);
    }

    @Override
    public ComplexLivingEntity getParent() {
        return (ComplexLivingEntity)((Object)this.getHandle().f_31010_.getBukkitEntity());
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent cause) {
        this.getParent().setLastDamageCause(cause);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return this.getParent().getLastDamageCause();
    }

    @Override
    public boolean isValid() {
        return this.getParent().isValid();
    }

    public EnderDragonPart getHandle() {
        return (EnderDragonPart)this.entity;
    }

    @Override
    public String toString() {
        return "CraftComplexPart";
    }
}

