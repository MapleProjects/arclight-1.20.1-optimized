/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Position
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.projectile.FishingHook
 *  net.minecraft.world.entity.projectile.Projectile
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftProjectile;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;

public class CraftFishHook
extends CraftProjectile
implements FishHook {
    private double biteChance = -1.0;

    public CraftFishHook(CraftServer server, FishingHook entity) {
        super(server, (Projectile)entity);
    }

    public FishingHook getHandle() {
        return (FishingHook)this.entity;
    }

    @Override
    public String toString() {
        return "CraftFishingHook";
    }

    @Override
    public int getMinWaitTime() {
        return this.getHandle().minWaitTime;
    }

    @Override
    public void setMinWaitTime(int minWaitTime) {
        Preconditions.checkArgument((minWaitTime >= 0 && minWaitTime <= this.getMaxWaitTime() ? 1 : 0) != 0, (String)"The minimum wait time should be between %s and %s (the maximum wait time)", (int)0, (int)this.getMaxWaitTime());
        FishingHook hook = this.getHandle();
        hook.minWaitTime = minWaitTime;
    }

    @Override
    public int getMaxWaitTime() {
        return this.getHandle().maxWaitTime;
    }

    @Override
    public void setMaxWaitTime(int maxWaitTime) {
        Preconditions.checkArgument((maxWaitTime >= 0 && maxWaitTime >= this.getMinWaitTime() ? 1 : 0) != 0, (String)"The maximum wait time should be between %s and %s (the minimum wait time)", (int)0, (int)this.getMinWaitTime());
        FishingHook hook = this.getHandle();
        hook.maxWaitTime = maxWaitTime;
    }

    @Override
    public void setWaitTime(int min, int max) {
        Preconditions.checkArgument((min >= 0 && max >= 0 && min <= max ? 1 : 0) != 0, (Object)"The minimum/maximum wait time should be higher than or equal to 0 and the minimum wait time");
        this.getHandle().minWaitTime = min;
        this.getHandle().maxWaitTime = max;
    }

    @Override
    public int getMinLureTime() {
        return this.getHandle().minLureTime;
    }

    @Override
    public void setMinLureTime(int minLureTime) {
        Preconditions.checkArgument((minLureTime >= 0 && minLureTime <= this.getMaxLureTime() ? 1 : 0) != 0, (String)"The minimum lure time (%s) should be between 0 and %s (the maximum wait time)", (int)minLureTime, (int)this.getMaxLureTime());
        this.getHandle().minLureTime = minLureTime;
    }

    @Override
    public int getMaxLureTime() {
        return this.getHandle().maxLureTime;
    }

    @Override
    public void setMaxLureTime(int maxLureTime) {
        Preconditions.checkArgument((maxLureTime >= 0 && maxLureTime >= this.getMinLureTime() ? 1 : 0) != 0, (String)"The maximum lure time (%s) should be higher than or equal to 0 and %s (the minimum wait time)", (int)maxLureTime, (int)this.getMinLureTime());
        this.getHandle().maxLureTime = maxLureTime;
    }

    @Override
    public void setLureTime(int min, int max) {
        Preconditions.checkArgument((min >= 0 && max >= 0 && min <= max ? 1 : 0) != 0, (Object)"The minimum/maximum lure time should be higher than or equal to 0 and the minimum wait time.");
        this.getHandle().minLureTime = min;
        this.getHandle().maxLureTime = max;
    }

    @Override
    public float getMinLureAngle() {
        return this.getHandle().minLureAngle;
    }

    @Override
    public void setMinLureAngle(float minLureAngle) {
        Preconditions.checkArgument((minLureAngle <= this.getMaxLureAngle() ? 1 : 0) != 0, (String)"The minimum lure angle (%s) should be less than %s (the maximum lure angle)", (Object)Float.valueOf(minLureAngle), (Object)Float.valueOf(this.getMaxLureAngle()));
        this.getHandle().minLureAngle = minLureAngle;
    }

    @Override
    public float getMaxLureAngle() {
        return this.getHandle().maxLureAngle;
    }

    @Override
    public void setMaxLureAngle(float maxLureAngle) {
        Preconditions.checkArgument((maxLureAngle >= this.getMinLureAngle() ? 1 : 0) != 0, (String)"The minimum lure angle (%s) should be less than %s (the maximum lure angle)", (Object)Float.valueOf(maxLureAngle), (Object)Float.valueOf(this.getMinLureAngle()));
        this.getHandle().maxLureAngle = maxLureAngle;
    }

    @Override
    public void setLureAngle(float min, float max) {
        Preconditions.checkArgument((min <= max ? 1 : 0) != 0, (String)"The minimum lure (%s) angle should be less than the maximum lure angle (%s)", (Object)Float.valueOf(min), (Object)Float.valueOf(max));
        this.getHandle().minLureAngle = min;
        this.getHandle().maxLureAngle = max;
    }

    @Override
    public boolean isSkyInfluenced() {
        return this.getHandle().skyInfluenced;
    }

    @Override
    public void setSkyInfluenced(boolean skyInfluenced) {
        this.getHandle().skyInfluenced = skyInfluenced;
    }

    @Override
    public boolean isRainInfluenced() {
        return this.getHandle().rainInfluenced;
    }

    @Override
    public void setRainInfluenced(boolean rainInfluenced) {
        this.getHandle().rainInfluenced = rainInfluenced;
    }

    @Override
    public boolean getApplyLure() {
        return this.getHandle().applyLure;
    }

    @Override
    public void setApplyLure(boolean applyLure) {
        this.getHandle().applyLure = applyLure;
    }

    @Override
    public double getBiteChance() {
        FishingHook hook = this.getHandle();
        if (this.biteChance == -1.0) {
            if (hook.m_9236_().m_46758_(BlockPos.m_274446_((Position)hook.m_20182_()).m_7918_(0, 1, 0))) {
                return 0.0033333333333333335;
            }
            return 0.002;
        }
        return this.biteChance;
    }

    @Override
    public void setBiteChance(double chance) {
        Preconditions.checkArgument((chance >= 0.0 && chance <= 1.0 ? 1 : 0) != 0, (Object)"The bite chance must be between 0 and 1");
        this.biteChance = chance;
    }

    @Override
    public boolean isInOpenWater() {
        return this.getHandle().m_37166_();
    }

    @Override
    public Entity getHookedEntity() {
        net.minecraft.world.entity.Entity hooked = this.getHandle().f_37094_;
        return hooked != null ? hooked.getBukkitEntity() : null;
    }

    @Override
    public void setHookedEntity(Entity entity) {
        FishingHook hook = this.getHandle();
        hook.f_37094_ = entity != null ? ((CraftEntity)entity).getHandle() : null;
        hook.m_20088_().m_135381_(FishingHook.f_37101_, (Object)(hook.f_37094_ != null ? hook.f_37094_.m_19879_() + 1 : 0));
    }

    @Override
    public boolean pullHookedEntity() {
        FishingHook hook = this.getHandle();
        if (hook.f_37094_ == null) {
            return false;
        }
        hook.m_150155_(hook.f_37094_);
        return true;
    }

    @Override
    public FishHook.HookState getState() {
        return FishHook.HookState.values()[this.getHandle().f_37095_.ordinal()];
    }
}

