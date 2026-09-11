/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.monster.EnderMan
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.entity.monster.EndermanEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import javax.annotation.Nullable;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={EnderMan.class})
public abstract class EnderManMixin
extends PathfinderMobMixin
implements EndermanEntityBridge {
    @Shadow
    private int f_32477_;
    @Shadow
    @Final
    private static EntityDataAccessor<Boolean> f_32473_;
    @Shadow
    @Final
    private static EntityDataAccessor<Boolean> f_32474_;
    @Shadow
    @Final
    private static AttributeModifier f_32481_;

    @Override
    public void bridge$updateTarget(LivingEntity livingEntity) {
        AttributeInstance modifiableattributeinstance = this.m_21051_(Attributes.f_22279_);
        if (livingEntity == null) {
            this.f_32477_ = 0;
            this.f_19804_.m_135381_(f_32473_, (Object)false);
            this.f_19804_.m_135381_(f_32474_, (Object)false);
            modifiableattributeinstance.m_22130_(f_32481_);
        } else {
            this.f_32477_ = this.f_19797_;
            this.f_19804_.m_135381_(f_32473_, (Object)true);
            if (!modifiableattributeinstance.m_22109_(f_32481_)) {
                modifiableattributeinstance.m_22118_(f_32481_);
            }
        }
    }

    @Override
    public boolean setTarget(LivingEntity livingEntity, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
        if (!super.setTarget(livingEntity, reason, fireEvent)) {
            return false;
        }
        this.bridge$updateTarget(this.m_5448_());
        return true;
    }

    @Override
    @Overwrite
    public void m_6710_(@Nullable LivingEntity entity) {
        this.bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true);
        super.m_6710_(entity);
        if (this.arclight$targetSuccess) {
            this.bridge$updateTarget(this.m_5448_());
        }
    }
}

