/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Explosion$BlockInteraction
 */
package io.izzel.arclight.common.bridge.core.world;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;

public interface ExplosionBridge {
    public Entity bridge$getExploder();

    public float bridge$getSize();

    public void bridge$setSize(float var1);

    public Explosion.BlockInteraction bridge$getMode();

    public boolean bridge$wasCancelled();
}

