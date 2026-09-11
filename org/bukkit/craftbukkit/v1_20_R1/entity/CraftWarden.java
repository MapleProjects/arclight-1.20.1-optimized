/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.warden.AngerLevel
 *  net.minecraft.world.entity.monster.warden.Warden
 *  net.minecraft.world.entity.monster.warden.WardenAi
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.AngerLevel;
import net.minecraft.world.entity.monster.warden.WardenAi;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Warden;

public class CraftWarden
extends CraftMonster
implements Warden {
    public CraftWarden(CraftServer server, net.minecraft.world.entity.monster.warden.Warden entity) {
        super(server, (Monster)entity);
    }

    public net.minecraft.world.entity.monster.warden.Warden getHandle() {
        return (net.minecraft.world.entity.monster.warden.Warden)this.entity;
    }

    @Override
    public String toString() {
        return "CraftWarden";
    }

    @Override
    public int getAnger() {
        return this.getHandle().m_219449_().m_219286_((net.minecraft.world.entity.Entity)this.getHandle().m_5448_());
    }

    @Override
    public int getAnger(Entity entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity cannot be null");
        return this.getHandle().m_219449_().m_219286_(((CraftEntity)entity).getHandle());
    }

    @Override
    public void increaseAnger(Entity entity, int increase) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity cannot be null");
        this.getHandle().m_219449_().m_219268_(((CraftEntity)entity).getHandle(), increase);
    }

    @Override
    public void setAnger(Entity entity, int anger) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity cannot be null");
        this.getHandle().m_219428_(((CraftEntity)entity).getHandle());
        this.getHandle().m_219449_().m_219268_(((CraftEntity)entity).getHandle(), anger);
    }

    @Override
    public void clearAnger(Entity entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Entity cannot be null");
        this.getHandle().m_219428_(((CraftEntity)entity).getHandle());
    }

    @Override
    public LivingEntity getEntityAngryAt() {
        return this.getHandle().m_219448_().map(net.minecraft.world.entity.Entity::getBukkitEntity).orElse(null);
    }

    @Override
    public void setDisturbanceLocation(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        WardenAi.m_219523_((net.minecraft.world.entity.monster.warden.Warden)this.getHandle(), (BlockPos)BlockPos.m_274561_((double)location.getX(), (double)location.getY(), (double)location.getZ()));
    }

    @Override
    public Warden.AngerLevel getAngerLevel() {
        return switch (this.getHandle().m_219446_()) {
            case AngerLevel.CALM -> Warden.AngerLevel.CALM;
            case AngerLevel.AGITATED -> Warden.AngerLevel.AGITATED;
            case AngerLevel.ANGRY -> Warden.AngerLevel.ANGRY;
            default -> throw new IncompatibleClassChangeError();
        };
    }
}

