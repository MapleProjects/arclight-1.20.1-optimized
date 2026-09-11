/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 *  net.minecraft.world.entity.monster.Skeleton
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Skeleton;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractSkeleton;
import org.bukkit.entity.Skeleton;

public class CraftSkeleton
extends CraftAbstractSkeleton
implements org.bukkit.entity.Skeleton {
    public CraftSkeleton(CraftServer server, Skeleton entity) {
        super(server, (AbstractSkeleton)entity);
    }

    @Override
    public boolean isConverting() {
        return this.getHandle().m_149839_();
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState((boolean)this.isConverting(), (Object)"Entity is not converting");
        return this.getHandle().f_149828_;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            this.getHandle().f_149828_ = -1;
            this.getHandle().m_20088_().m_135381_(Skeleton.f_149826_, (Object)false);
        } else {
            this.getHandle().m_149830_(time);
        }
    }

    public Skeleton getHandle() {
        return (Skeleton)this.entity;
    }

    @Override
    public String toString() {
        return "CraftSkeleton";
    }

    @Override
    public Skeleton.SkeletonType getSkeletonType() {
        return Skeleton.SkeletonType.NORMAL;
    }
}

