/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.item.MinecartItem
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.mod.util.DistValidate;
import io.izzel.arclight.mixin.Eject;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={MinecartItem.class})
public class MinecartItemMixin {
    @Eject(method={"useOn"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$entityPlace(Level world, Entity entityIn, CallbackInfoReturnable<InteractionResult> cir, UseOnContext context) {
        if (DistValidate.isValid((LevelAccessor)world) && CraftEventFactory.callEntityPlaceEvent(context, entityIn).isCancelled()) {
            cir.setReturnValue((Object)InteractionResult.FAIL);
            return false;
        }
        if (!world.m_7967_(entityIn)) {
            cir.setReturnValue((Object)InteractionResult.PASS);
            return false;
        }
        return true;
    }
}

