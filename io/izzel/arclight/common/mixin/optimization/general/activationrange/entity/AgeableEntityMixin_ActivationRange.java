/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.AgeableMob
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange.entity;

import io.izzel.arclight.common.bridge.core.entity.AgeableEntityBridge;
import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import net.minecraft.world.entity.AgeableMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={AgeableMob.class})
public abstract class AgeableEntityMixin_ActivationRange
extends EntityMixin_ActivationRange {
    @Shadow
    public abstract int m_146764_();

    @Shadow
    public abstract void m_146762_(int var1);

    @Override
    public void inactiveTick() {
        super.inactiveTick();
        if (((AgeableEntityBridge)((Object)this)).bridge$isAgeLocked()) {
            this.m_6210_();
        } else {
            int i = this.m_146764_();
            if (i < 0) {
                this.m_146762_(++i);
            } else if (i > 0) {
                this.m_146762_(--i);
            }
        }
    }
}

