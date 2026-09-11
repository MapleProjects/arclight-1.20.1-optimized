/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Witch
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.mixin.core.world.entity.raider.RaiderMixin;
import net.minecraft.world.entity.monster.Witch;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Witch.class})
public abstract class WitchMixin
extends RaiderMixin {
    @Inject(method={"aiStep"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/Witch;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z")})
    private void arclight$reason(CallbackInfo ci) {
        this.bridge$pushEffectCause(EntityPotionEffectEvent.Cause.ATTACK);
    }
}

