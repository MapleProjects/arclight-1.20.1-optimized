/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.FlintAndSteelItem
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.BlockIgniteEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={FlintAndSteelItem.class})
public class FlintAndSteelItemMixin {
    @Inject(method={"useOn"}, cancellable=true, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")})
    public void arclight$blockIgnite(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        BlockPos blockpos;
        BlockPos blockpos1;
        if (!DistValidate.isValid(context)) {
            return;
        }
        Player playerentity = context.m_43723_();
        Level world = context.m_43725_();
        if (CraftEventFactory.callBlockIgniteEvent(world, blockpos1 = (blockpos = context.m_8083_()).m_121945_(context.m_43719_()), BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL, (Entity)playerentity).isCancelled()) {
            context.m_43722_().m_41622_(1, (LivingEntity)playerentity, entity -> entity.m_21190_(context.m_43724_()));
            cir.setReturnValue((Object)InteractionResult.PASS);
        }
    }
}

