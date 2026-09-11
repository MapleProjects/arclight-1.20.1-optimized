/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.AreaEffectCloud
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange.entity;

import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import net.minecraft.world.entity.AreaEffectCloud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={AreaEffectCloud.class})
public abstract class AreaEffectCloudEntityMixin_ActivationRange
extends EntityMixin_ActivationRange {
    @Shadow
    public int f_19688_;
    @Shadow
    private int f_19687_;

    @Override
    public void inactiveTick() {
        super.inactiveTick();
        if (this.f_19797_ >= this.f_19688_ + this.f_19687_) {
            this.m_146870_();
        }
    }
}

