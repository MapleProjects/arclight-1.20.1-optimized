/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange.entity;

import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={AbstractArrow.class})
public abstract class AbstractArrowEntityMixin_ActivationRange
extends EntityMixin_ActivationRange {
    @Shadow
    public boolean f_36703_;
    @Shadow
    protected int f_36704_;

    @Override
    public void inactiveTick() {
        super.inactiveTick();
        if (this.f_36703_) {
            ++this.f_36704_;
        }
    }
}

