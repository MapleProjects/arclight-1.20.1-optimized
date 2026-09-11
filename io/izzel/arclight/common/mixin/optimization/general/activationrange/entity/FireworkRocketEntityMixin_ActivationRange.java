/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange.entity;

import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={FireworkRocketEntity.class})
public abstract class FireworkRocketEntityMixin_ActivationRange
extends EntityMixin_ActivationRange {
    @Shadow
    private int f_37022_;
    @Shadow
    public int f_37023_;

    @Shadow
    protected abstract void m_37080_();

    @Override
    public void inactiveTick() {
        super.inactiveTick();
        ++this.f_37022_;
        if (!this.m_9236_().f_46443_ && this.f_37022_ > this.f_37023_ && !CraftEventFactory.callFireworkExplodeEvent((FireworkRocketEntity)this).isCancelled()) {
            this.m_37080_();
        }
    }
}

