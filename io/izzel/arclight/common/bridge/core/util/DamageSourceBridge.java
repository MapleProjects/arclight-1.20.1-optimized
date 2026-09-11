/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 */
package io.izzel.arclight.common.bridge.core.util;

import net.minecraft.world.damagesource.DamageSource;

public interface DamageSourceBridge {
    public boolean bridge$isSweep();

    public DamageSource bridge$sweep();

    public DamageSource bridge$poison();

    public DamageSource bridge$melting();
}

