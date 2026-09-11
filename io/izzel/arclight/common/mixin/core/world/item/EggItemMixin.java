/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.EggItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.mixin.Eject;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EggItem.class})
public abstract class EggItemMixin
extends Item {
    public EggItemMixin(Item.Properties properties) {
        super(properties);
    }

    @Redirect(method={"use"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
    private void arclight$muteSound(Level world, Player player, double x, double y, double z, SoundEvent soundIn, SoundSource category, float volume, float pitch) {
    }

    @Eject(method={"use"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$updateIfFail(Level world, Entity entityIn, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        if (!worldIn.m_7967_(entityIn)) {
            if (playerIn instanceof ServerPlayerEntityBridge) {
                ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().updateInventory();
            }
            cir.setReturnValue((Object)InteractionResultHolder.m_19100_((Object)playerIn.m_21120_(handIn)));
            return false;
        }
        worldIn.m_6263_(null, playerIn.m_20185_(), playerIn.m_20186_(), playerIn.m_20189_(), SoundEvents.f_11877_, SoundSource.PLAYERS, 0.5f, 0.4f / (world.m_213780_().m_188501_() * 0.4f + 0.8f));
        return true;
    }
}

