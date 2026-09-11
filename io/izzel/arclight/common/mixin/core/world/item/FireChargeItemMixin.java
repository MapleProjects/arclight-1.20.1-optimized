/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.FireChargeItem
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.BlockIgniteEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={FireChargeItem.class})
public class FireChargeItemMixin {
    @Inject(method={"useOn"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/item/FireChargeItem;playSound(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V")})
    public void arclight$blockIgnite(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, Level world, BlockPos blockPos) {
        if (DistValidate.isValid(context) && CraftEventFactory.callBlockIgniteEvent(world, blockPos, BlockIgniteEvent.IgniteCause.FIREBALL, (Entity)context.m_43723_()).isCancelled()) {
            if (!context.m_43723_().m_150110_().f_35937_) {
                context.m_43722_().m_41774_(1);
            }
            cir.setReturnValue((Object)InteractionResult.PASS);
        }
    }
}

