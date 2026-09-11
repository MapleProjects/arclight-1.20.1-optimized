/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange.entity;

import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={LivingEntity.class})
public abstract class LivingEntityMixin_ActivationRange
extends EntityMixin_ActivationRange {
    @Shadow
    protected int f_20891_;

    @Override
    public void inactiveTick() {
        super.inactiveTick();
        ++this.f_20891_;
    }
}

