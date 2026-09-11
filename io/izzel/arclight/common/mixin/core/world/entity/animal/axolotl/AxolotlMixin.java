/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.axolotl.Axolotl
 *  net.minecraft.world.entity.player.Player
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal.axolotl;

import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Axolotl.class})
public abstract class AxolotlMixin
extends AnimalMixin {
    @Shadow
    @Final
    private static int f_149099_;

    @Inject(method={"getMaxAirSupply"}, cancellable=true, at={@At(value="RETURN")})
    private void arclight$useBukkitMaxAir(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((Object)this.maxAirTicks);
    }

    @Override
    public int getDefaultMaxAirSupply() {
        return f_149099_;
    }

    @Inject(method={"applySupportingEffects"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$cause(Player player, CallbackInfo ci) {
        ((MobEntityBridge)player).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.AXOLOTL);
    }
}

