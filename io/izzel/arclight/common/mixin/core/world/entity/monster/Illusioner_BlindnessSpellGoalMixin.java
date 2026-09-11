/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Illusioner
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import net.minecraft.world.entity.monster.Illusioner;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net.minecraft.world.entity.monster.Illusioner$IllusionerBlindnessSpellGoal"})
public class Illusioner_BlindnessSpellGoalMixin {
    @Shadow(aliases={"this$0", "f_32941_"}, remap=false)
    private Illusioner outerThis;

    @Inject(method={"performSpellCasting"}, at={@At(value="HEAD")})
    private void arclight$reason(CallbackInfo ci) {
        ((LivingEntityBridge)this.outerThis).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.ATTACK);
    }
}

