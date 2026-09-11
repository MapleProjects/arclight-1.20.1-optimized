/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.Holder
 *  net.minecraft.server.commands.EffectCommands
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.server.commands.EffectCommands;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EffectCommands.class})
public class EffectCommandMixin {
    @Inject(method={"giveEffect"}, at={@At(value="HEAD")})
    private static void arclight$addReason(CommandSourceStack p_250553_, Collection<? extends Entity> targets, Holder<MobEffect> p_249495_, Integer p_249652_, int p_251498_, boolean p_249944_, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : targets) {
            if (!(entity instanceof LivingEntity)) continue;
            ((LivingEntityBridge)entity).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.COMMAND);
        }
    }

    @Inject(method={"clearEffects"}, at={@At(value="HEAD")})
    private static void arclight$removeAllReason(CommandSourceStack source, Collection<? extends Entity> targets, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : targets) {
            if (!(entity instanceof LivingEntity)) continue;
            ((LivingEntityBridge)entity).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.COMMAND);
        }
    }

    @Inject(method={"clearEffect"}, at={@At(value="HEAD")})
    private static void arclight$removeReason(CommandSourceStack p_250069_, Collection<? extends Entity> targets, Holder<MobEffect> p_249198_, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : targets) {
            if (!(entity instanceof LivingEntity)) continue;
            ((LivingEntityBridge)entity).bridge$pushEffectCause(EntityPotionEffectEvent.Cause.COMMAND);
        }
    }
}

