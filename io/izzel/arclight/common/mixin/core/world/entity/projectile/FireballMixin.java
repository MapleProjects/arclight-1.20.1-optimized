/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.projectile.Fireball
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.mixin.core.world.entity.projectile.AbstractHurtingProjectileMixin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={Fireball.class})
public abstract class FireballMixin
extends AbstractHurtingProjectileMixin {
    @Inject(method={"readAdditionalSaveData"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/projectile/Fireball;setItem(Lnet/minecraft/world/item/ItemStack;)V")})
    private void arclight$nonNullItem(CompoundTag compound, CallbackInfo ci, ItemStack stack) {
        if (stack.m_41619_()) {
            ci.cancel();
        }
    }
}

