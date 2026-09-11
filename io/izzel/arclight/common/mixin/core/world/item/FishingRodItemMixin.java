/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.FishingHook
 *  net.minecraft.world.item.FishingRodItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.gameevent.GameEvent
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.entity.FishHook;
import org.bukkit.event.player.PlayerFishEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={FishingRodItem.class})
public class FishingRodItemMixin
extends Item {
    public FishingRodItemMixin(Item.Properties properties) {
        super(properties);
    }

    @Overwrite
    public InteractionResultHolder<ItemStack> m_7203_(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.m_21120_(handIn);
        if (playerIn.f_36083_ != null) {
            if (!worldIn.f_46443_) {
                int i = playerIn.f_36083_.m_37156_(itemstack);
                itemstack.m_41622_(i, (LivingEntity)playerIn, player -> player.m_21190_(handIn));
            }
            playerIn.m_6674_(handIn);
            worldIn.m_6263_(null, playerIn.m_20185_(), playerIn.m_20186_(), playerIn.m_20189_(), SoundEvents.f_11939_, SoundSource.NEUTRAL, 1.0f, 0.4f / (worldIn.m_213780_().m_188501_() * 0.4f + 0.8f));
            playerIn.m_146850_(GameEvent.f_223697_);
        } else {
            if (!worldIn.f_46443_) {
                int k = EnchantmentHelper.m_44916_((ItemStack)itemstack);
                int j = EnchantmentHelper.m_44904_((ItemStack)itemstack);
                FishingHook hook = new FishingHook(playerIn, worldIn, j, k);
                if (DistValidate.isValid((LevelAccessor)worldIn)) {
                    PlayerFishEvent playerFishEvent = new PlayerFishEvent(((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity(), null, (FishHook)((Object)((EntityBridge)hook).bridge$getBukkitEntity()), CraftEquipmentSlot.getHand(handIn), PlayerFishEvent.State.FISHING);
                    Bukkit.getPluginManager().callEvent(playerFishEvent);
                    if (playerFishEvent.isCancelled()) {
                        playerIn.f_36083_ = null;
                        return new InteractionResultHolder(InteractionResult.PASS, (Object)itemstack);
                    }
                }
                worldIn.m_6263_(null, playerIn.m_20185_(), playerIn.m_20186_(), playerIn.m_20189_(), SoundEvents.f_11941_, SoundSource.NEUTRAL, 0.5f, 0.4f / (worldIn.m_213780_().m_188501_() * 0.4f + 0.8f));
                worldIn.m_7967_((Entity)new FishingHook(playerIn, worldIn, j, k));
            }
            playerIn.m_36246_(Stats.f_12982_.m_12902_((Object)this));
            playerIn.m_146850_(GameEvent.f_223698_);
        }
        return InteractionResultHolder.m_19092_((Object)itemstack, (boolean)worldIn.m_5776_());
    }
}

