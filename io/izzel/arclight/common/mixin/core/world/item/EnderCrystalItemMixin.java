/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.boss.enderdragon.EndCrystal
 *  net.minecraft.world.item.EndCrystalItem
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
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.item.EndCrystalItem;
import net.minecraft.world.item.context.UseOnContext;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EndCrystalItem.class})
public class EnderCrystalItemMixin {
    private transient EndCrystal arclight$enderCrystalEntity;

    @Redirect(method={"useOn"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;setShowBottom(Z)V"))
    public void arclight$captureEntity(EndCrystal enderCrystalEntity, boolean showBottom) {
        this.arclight$enderCrystalEntity = enderCrystalEntity;
        enderCrystalEntity.m_31056_(showBottom);
    }

    @Inject(method={"useOn"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    public void arclight$entityPlace(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (DistValidate.isValid(context) && CraftEventFactory.callEntityPlaceEvent(context, (Entity)this.arclight$enderCrystalEntity).isCancelled()) {
            cir.setReturnValue((Object)InteractionResult.FAIL);
        }
        this.arclight$enderCrystalEntity = null;
    }
}

