/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.boss.wither.WitherBoss
 *  net.minecraft.world.entity.monster.Monster
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.boss.CraftBossBar;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;

public class CraftWither
extends CraftMonster
implements Wither {
    private BossBar bossBar;

    public CraftWither(CraftServer server, WitherBoss entity) {
        super(server, (Monster)entity);
        if (entity.f_31430_ != null) {
            this.bossBar = new CraftBossBar(entity.f_31430_);
        }
    }

    public WitherBoss getHandle() {
        return (WitherBoss)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWither";
    }

    @Override
    public BossBar getBossBar() {
        return this.bossBar;
    }

    @Override
    public void setTarget(Wither.Head head, LivingEntity livingEntity) {
        Preconditions.checkArgument((head != null ? 1 : 0) != 0, (Object)"head cannot be null");
        int entityId = livingEntity != null ? livingEntity.getEntityId() : 0;
        this.getHandle().m_31454_(head.ordinal(), entityId);
    }

    @Override
    public LivingEntity getTarget(Wither.Head head) {
        Preconditions.checkArgument((head != null ? 1 : 0) != 0, (Object)"head cannot be null");
        int entityId = this.getHandle().m_31512_(head.ordinal());
        if (entityId == 0) {
            return null;
        }
        Entity target = this.getHandle().m_9236_().m_6815_(entityId);
        return target != null ? (LivingEntity)((Object)target.getBukkitEntity()) : null;
    }

    @Override
    public int getInvulnerabilityTicks() {
        return this.getHandle().m_31502_();
    }

    @Override
    public void setInvulnerabilityTicks(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"ticks must be >=0");
        this.getHandle().m_31510_(ticks);
    }
}

