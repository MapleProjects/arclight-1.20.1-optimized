/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.decoration;

import io.izzel.arclight.common.mixin.core.world.entity.item.HangingEntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ItemFrame.class})
public abstract class ItemFrameMixin
extends HangingEntityMixin {
    @Shadow
    @Final
    private static EntityDataAccessor<ItemStack> f_31757_;

    @Shadow
    protected abstract void m_218865_(ItemStack var1);

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/ItemFrame;dropItem(Lnet/minecraft/world/entity/Entity;Z)V")})
    private void arclight$damageNonLiving(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (CraftEventFactory.handleNonLivingEntityDamageEvent((Entity)((ItemFrame)this), source, amount, false) || this.m_213877_()) {
            cir.setReturnValue((Object)true);
        }
    }

    public void setItem(ItemStack itemstack, boolean flag, boolean playSound) {
        if (!itemstack.m_41619_()) {
            itemstack = itemstack.m_41777_();
            itemstack.m_41764_(1);
        }
        this.m_218865_(itemstack);
        this.m_20088_().m_135381_(f_31757_, (Object)itemstack);
        if (!itemstack.m_41619_() && playSound) {
            this.m_5496_(SoundEvents.f_12013_, 1.0f, 1.0f);
        }
        if (flag && this.f_31698_ != null) {
            this.m_9236_().m_46717_(this.f_31698_, Blocks.f_50016_);
        }
    }

    private static AABB calculateBoundingBox(Entity entity, BlockPos blockPosition, Direction direction, int width, int height) {
        double d0 = 0.46875;
        double locX = (double)blockPosition.m_123341_() + 0.5 - (double)direction.m_122429_() * 0.46875;
        double locY = (double)blockPosition.m_123342_() + 0.5 - (double)direction.m_122430_() * 0.46875;
        double locZ = (double)blockPosition.m_123343_() + 0.5 - (double)direction.m_122431_() * 0.46875;
        if (entity != null) {
            entity.m_20343_(locX, locY, locZ);
        }
        double d2 = width;
        double d3 = height;
        double d4 = width;
        Direction.Axis enumdirection_enumaxis = direction.m_122434_();
        switch (enumdirection_enumaxis) {
            case X: {
                d2 = 1.0;
                break;
            }
            case Y: {
                d3 = 1.0;
                break;
            }
            case Z: {
                d4 = 1.0;
            }
        }
        return new AABB(locX - (d2 /= 32.0), locY - (d3 /= 32.0), locZ - (d4 /= 32.0), locX + d2, locY + d3, locZ + d4);
    }
}

