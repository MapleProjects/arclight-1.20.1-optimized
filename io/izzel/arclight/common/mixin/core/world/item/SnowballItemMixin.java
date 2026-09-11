/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.Snowball
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.SnowballItem
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={SnowballItem.class})
public class SnowballItemMixin
extends Item {
    public SnowballItemMixin(Item.Properties properties) {
        super(properties);
    }

    @Overwrite
    @NotNull
    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, @NotNull InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        if (!worldIn.f_46443_) {
            Snowball snowballentity = new Snowball(worldIn, (LivingEntity)playerIn);
            snowballentity.m_37446_(itemstack);
            snowballentity.m_37251_((Entity)playerIn, playerIn.m_146909_(), playerIn.m_146908_(), 0.0f, 1.5f, 1.0f);
            if (worldIn.m_7967_((Entity)snowballentity)) {
                if (!playerIn.m_150110_().f_35937_) {
                    itemstack.m_41774_(1);
                }
                worldIn.m_6263_(null, playerIn.m_20185_(), playerIn.m_20186_(), playerIn.m_20189_(), SoundEvents.f_12473_, SoundSource.NEUTRAL, 0.5f, 0.4f / (worldIn.m_213780_().m_188501_() * 0.4f + 0.8f));
            } else if (playerIn instanceof ServerPlayer) {
                ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().updateInventory();
            }
        }
        playerIn.m_36246_(Stats.f_12982_.m_12902_((Object)this));
        return new InteractionResultHolder(InteractionResult.SUCCESS, (Object)itemstack);
    }
}

