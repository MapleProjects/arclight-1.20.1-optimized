/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Phantom
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import net.minecraft.world.entity.monster.Phantom;
import org.bukkit.event.entity.EntityTargetEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets={"net.minecraft.world.entity.monster.Phantom$PhantomAttackPlayerTargetGoal"})
public abstract class Phantom_AttackPlayerTargetGoalMixin {
    @Shadow(aliases={"this$0", "f_33191_"}, remap=false)
    private Phantom outerThis;

    @Inject(method={"m_8036_()Z"}, remap=false, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/Phantom;m_6710_(Lnet/minecraft/world/entity/LivingEntity;)V")})
    private void arclight$reason(CallbackInfoReturnable<Boolean> cir) {
        ((MobEntityBridge)this.outerThis).bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true);
    }
}

