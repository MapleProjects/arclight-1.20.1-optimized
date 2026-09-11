/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.Guardian
 *  net.minecraft.world.entity.monster.Guardian$GuardianAttackGoal
 *  net.minecraft.world.entity.monster.Monster
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMonster;
import org.bukkit.entity.LivingEntity;

public class CraftGuardian
extends CraftMonster
implements org.bukkit.entity.Guardian {
    private static final int MINIMUM_ATTACK_TICKS = -10;

    public CraftGuardian(CraftServer server, Guardian entity) {
        super(server, (Monster)entity);
    }

    public Guardian getHandle() {
        return (Guardian)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftGuardian";
    }

    @Override
    public void setTarget(LivingEntity target) {
        super.setTarget(target);
        if (target == null) {
            this.getHandle().m_32817_(0);
        }
    }

    @Override
    public boolean setLaser(boolean activated) {
        if (activated) {
            CraftLivingEntity target = this.getTarget();
            if (target == null) {
                return false;
            }
            this.getHandle().m_32817_(target.getEntityId());
        } else {
            this.getHandle().m_32817_(0);
        }
        return true;
    }

    @Override
    public boolean hasLaser() {
        return this.getHandle().m_32855_();
    }

    @Override
    public int getLaserDuration() {
        return this.getHandle().m_7552_();
    }

    @Override
    public void setLaserTicks(int ticks) {
        Preconditions.checkArgument((ticks >= -10 ? 1 : 0) != 0, (String)"ticks must be >= %s. Given %s", (int)-10, (int)ticks);
        Guardian.GuardianAttackGoal goal = this.getHandle().guardianAttackGoal;
        if (goal != null) {
            goal.f_32868_ = ticks;
        }
    }

    @Override
    public int getLaserTicks() {
        Guardian.GuardianAttackGoal goal = this.getHandle().guardianAttackGoal;
        return goal != null ? goal.f_32868_ : -10;
    }

    @Override
    public boolean isElder() {
        return false;
    }

    @Override
    public void setElder(boolean shouldBeElder) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isMoving() {
        return this.getHandle().m_32854_();
    }
}

