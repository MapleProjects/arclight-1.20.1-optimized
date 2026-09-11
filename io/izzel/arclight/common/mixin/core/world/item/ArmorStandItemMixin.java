/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.decoration.ArmorStand
 *  net.minecraft.world.item.ArmorStandItem
 *  net.minecraft.world.item.context.UseOnContext
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ArmorStandItem;
import net.minecraft.world.item.context.UseOnContext;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ArmorStandItem.class})
public class ArmorStandItemMixin {
    private transient ArmorStand arclight$entity;

    @Redirect(method={"useOn"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/ArmorStand;moveTo(DDDFF)V"))
    public void arclight$captureEntity(ArmorStand armorStandEntity, double x, double y, double z, float yaw, float pitch) {
        armorStandEntity.m_7678_(x, y, z, yaw, pitch);
        this.arclight$entity = armorStandEntity;
    }

    @Inject(method={"useOn"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V")})
    public void arclight$entityPlace(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (DistValidate.isValid(context) && CraftEventFactory.callEntityPlaceEvent(context, (Entity)this.arclight$entity).isCancelled()) {
            cir.setReturnValue((Object)InteractionResult.FAIL);
        }
        this.arclight$entity = null;
    }
}

