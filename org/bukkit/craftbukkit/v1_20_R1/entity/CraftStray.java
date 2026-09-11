/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 *  net.minecraft.world.entity.monster.Stray
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractSkeleton;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Stray;

public class CraftStray
extends CraftAbstractSkeleton
implements Stray {
    public CraftStray(CraftServer server, net.minecraft.world.entity.monster.Stray entity) {
        super(server, (AbstractSkeleton)entity);
    }

    @Override
    public String toString() {
        return "CraftStray";
    }

    @Override
    public Skeleton.SkeletonType getSkeletonType() {
        return Skeleton.SkeletonType.STRAY;
    }
}

