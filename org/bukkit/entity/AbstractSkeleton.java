/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface AbstractSkeleton
extends Monster {
    @Deprecated
    @NotNull
    public Skeleton.SkeletonType getSkeletonType();

    @Deprecated
    @Contract(value="_ -> fail")
    public void setSkeletonType(Skeleton.SkeletonType var1);
}

