/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.entity;

import org.bukkit.entity.AbstractSkeleton;

public interface Skeleton
extends AbstractSkeleton {
    public boolean isConverting();

    public int getConversionTime();

    public void setConversionTime(int var1);

    @Deprecated
    public static enum SkeletonType {
        NORMAL,
        WITHER,
        STRAY;

    }
}

