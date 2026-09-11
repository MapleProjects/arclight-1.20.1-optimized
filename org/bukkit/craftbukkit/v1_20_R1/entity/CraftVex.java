/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Vex
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;

public class CraftVex
extends CraftMonster
implements org.bukkit.entity.Vex {
    public CraftVex(CraftServer server, Vex entity) {
        super(server, (Monster)entity);
    }

    public Vex getHandle() {
        return (Vex)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftVex";
    }

    @Override
    public boolean isCharging() {
        return this.getHandle().m_34028_();
    }

    @Override
    public void setCharging(boolean charging) {
        this.getHandle().m_34042_(charging);
    }

    @Override
    public Location getBound() {
        BlockPos blockPosition = this.getHandle().m_34027_();
        return blockPosition == null ? null : CraftLocation.toBukkit(blockPosition, this.getWorld());
    }

    @Override
    public void setBound(Location location) {
        if (location == null) {
            this.getHandle().m_34033_(null);
        } else {
            Preconditions.checkArgument((boolean)this.getWorld().equals(location.getWorld()), (Object)"The bound world cannot be different to the entity's world.");
            this.getHandle().m_34033_(CraftLocation.toBlockPosition(location));
        }
    }

    @Override
    public int getLifeTicks() {
        return this.getHandle().f_33979_;
    }

    @Override
    public void setLifeTicks(int lifeTicks) {
        this.getHandle().m_33987_(lifeTicks);
        if (lifeTicks < 0) {
            this.getHandle().f_33978_ = false;
        }
    }

    @Override
    public boolean hasLimitedLife() {
        return this.getHandle().f_33978_;
    }
}

