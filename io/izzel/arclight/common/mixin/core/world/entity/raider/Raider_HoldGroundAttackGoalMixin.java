/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.raid.Raider
 *  net.minecraft.world.entity.raid.Raider$HoldGroundAttackGoal
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.raider;

import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.raid.Raider;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Raider.HoldGroundAttackGoal.class})
public abstract class Raider_HoldGroundAttackGoalMixin {
    @Redirect(method={"start"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raider;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void arclight$reason(Raider abstractRaiderEntity, LivingEntity entitylivingbaseIn) {
        ((MobEntityBridge)abstractRaiderEntity).bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.FOLLOW_LEADER, true);
        abstractRaiderEntity.m_6710_(entitylivingbaseIn);
    }

    @Redirect(method={"stop"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raider;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void arclight$reason2(Raider abstractRaiderEntity, LivingEntity entitylivingbaseIn) {
        ((MobEntityBridge)abstractRaiderEntity).bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.FOLLOW_LEADER, true);
        abstractRaiderEntity.m_6710_(entitylivingbaseIn);
    }
}

