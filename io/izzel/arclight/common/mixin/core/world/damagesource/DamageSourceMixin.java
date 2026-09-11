/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.damagesource;

import io.izzel.arclight.common.bridge.core.util.DamageSourceBridge;
import javax.annotation.Nullable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={DamageSource.class})
public abstract class DamageSourceMixin
implements DamageSourceBridge {
    private boolean sweep;
    private boolean melting;
    private boolean poison;

    @Shadow
    @Nullable
    public Entity m_7639_() {
        return null;
    }

    public boolean isSweep() {
        return this.sweep;
    }

    @Override
    public boolean bridge$isSweep() {
        return this.isSweep();
    }

    public DamageSource sweep() {
        this.sweep = true;
        return (DamageSource)this;
    }

    @Override
    public DamageSource bridge$sweep() {
        return this.sweep();
    }

    public boolean isMelting() {
        return this.melting;
    }

    public DamageSource melting() {
        this.melting = true;
        return (DamageSource)this;
    }

    @Override
    public DamageSource bridge$melting() {
        return this.melting();
    }

    public boolean isPoison() {
        return this.poison;
    }

    public DamageSource poison() {
        this.poison = true;
        return (DamageSource)this;
    }

    @Override
    public DamageSource bridge$poison() {
        return this.poison();
    }
}

