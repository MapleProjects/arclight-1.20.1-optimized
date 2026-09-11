/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Monster
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.Creeper;
import org.bukkit.event.entity.CreeperPowerEvent;

public class CraftCreeper
extends CraftMonster
implements Creeper {
    public CraftCreeper(CraftServer server, net.minecraft.world.entity.monster.Creeper entity) {
        super(server, (Monster)entity);
    }

    @Override
    public boolean isPowered() {
        return this.getHandle().m_7090_();
    }

    @Override
    public void setPowered(boolean powered) {
        CreeperPowerEvent.PowerCause cause;
        CreeperPowerEvent.PowerCause powerCause = cause = powered ? CreeperPowerEvent.PowerCause.SET_ON : CreeperPowerEvent.PowerCause.SET_OFF;
        if (this.getHandle().generation || !this.callPowerEvent(cause)) {
            this.getHandle().setPowered(powered);
        }
    }

    private boolean callPowerEvent(CreeperPowerEvent.PowerCause cause) {
        CreeperPowerEvent event = new CreeperPowerEvent((Creeper)((Object)this.getHandle().getBukkitEntity()), cause);
        this.server.getPluginManager().callEvent(event);
        return event.isCancelled();
    }

    @Override
    public void setMaxFuseTicks(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"ticks < 0");
        this.getHandle().f_32271_ = ticks;
    }

    @Override
    public int getMaxFuseTicks() {
        return this.getHandle().f_32271_;
    }

    @Override
    public void setFuseTicks(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"ticks < 0");
        Preconditions.checkArgument((ticks <= this.getMaxFuseTicks() ? 1 : 0) != 0, (Object)"ticks > maxFuseTicks");
        this.getHandle().f_32270_ = ticks;
    }

    @Override
    public int getFuseTicks() {
        return this.getHandle().f_32270_;
    }

    @Override
    public void setExplosionRadius(int radius) {
        Preconditions.checkArgument((radius >= 0 ? 1 : 0) != 0, (Object)"radius < 0");
        this.getHandle().f_32272_ = radius;
    }

    @Override
    public int getExplosionRadius() {
        return this.getHandle().f_32272_;
    }

    @Override
    public void explode() {
        this.getHandle().m_32315_();
    }

    @Override
    public void ignite() {
        this.getHandle().m_32312_();
    }

    public net.minecraft.world.entity.monster.Creeper getHandle() {
        return (net.minecraft.world.entity.monster.Creeper)this.entity;
    }

    @Override
    public String toString() {
        return "CraftCreeper";
    }
}

