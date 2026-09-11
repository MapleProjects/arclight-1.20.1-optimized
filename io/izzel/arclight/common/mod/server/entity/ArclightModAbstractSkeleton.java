/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.AbstractSkeleton
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.server.entity;

import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAbstractSkeleton;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

public class ArclightModAbstractSkeleton
extends CraftAbstractSkeleton {
    public ArclightModAbstractSkeleton(CraftServer server, AbstractSkeleton entity) {
        super(server, entity);
    }

    @Override
    @NotNull
    public Skeleton.SkeletonType getSkeletonType() {
        return Skeleton.SkeletonType.NORMAL;
    }

    @Override
    public void setSkeletonType(Skeleton.SkeletonType type) {
    }
}

