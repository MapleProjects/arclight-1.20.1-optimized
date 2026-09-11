/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.item.ArrowItem
 *  net.minecraft.world.item.BowItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.ProjectileWeaponItem
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.item.enchantment.Enchantments
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={BowItem.class})
public abstract class BowItemMixin
extends ProjectileWeaponItem {
    public BowItemMixin(Item.Properties builder) {
        super(builder);
    }

    @Shadow
    public abstract int m_8105_(ItemStack var1);

    @Shadow
    public static float m_40661_(int charge) {
        return 0.0f;
    }

    @Shadow(remap=false)
    public abstract AbstractArrow customArrow(AbstractArrow var1);

    @Overwrite
    public void m_5551_(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player) {
            Player playerentity = (Player)entityLiving;
            boolean flag = playerentity.m_150110_().f_35937_ || EnchantmentHelper.m_44843_((Enchantment)Enchantments.f_44952_, (ItemStack)stack) > 0;
            ItemStack itemstack = playerentity.m_6298_(stack);
            int i = this.m_8105_(stack) - timeLeft;
            if ((i = ForgeEventFactory.onArrowLoose((ItemStack)stack, (Level)worldIn, (Player)playerentity, (int)i, (!itemstack.m_41619_() || flag ? 1 : 0) != 0)) < 0) {
                return;
            }
            if (!itemstack.m_41619_() || flag) {
                float f;
                if (itemstack.m_41619_()) {
                    itemstack = new ItemStack((ItemLike)Items.f_42412_);
                }
                if (!((double)(f = BowItemMixin.m_40661_(i)) < 0.1)) {
                    boolean flag1;
                    boolean bl = flag1 = playerentity.m_150110_().f_35937_ || itemstack.m_41720_() instanceof ArrowItem && ((ArrowItem)itemstack.m_41720_()).isInfinite(itemstack, stack, playerentity);
                    if (!worldIn.f_46443_) {
                        EntityShootBowEvent event;
                        int k;
                        int j;
                        ArrowItem arrowitem = (ArrowItem)(itemstack.m_41720_() instanceof ArrowItem ? itemstack.m_41720_() : Items.f_42412_);
                        AbstractArrow abstractarrowentity = arrowitem.m_6394_(worldIn, itemstack, (LivingEntity)playerentity);
                        abstractarrowentity = this.customArrow(abstractarrowentity);
                        abstractarrowentity.m_37251_((Entity)playerentity, playerentity.m_146909_(), playerentity.m_146908_(), 0.0f, f * 3.0f, 1.0f);
                        if (f == 1.0f) {
                            abstractarrowentity.m_36762_(true);
                        }
                        if ((j = EnchantmentHelper.m_44843_((Enchantment)Enchantments.f_44988_, (ItemStack)stack)) > 0) {
                            abstractarrowentity.m_36781_(abstractarrowentity.m_36789_() + (double)j * 0.5 + 0.5);
                        }
                        if ((k = EnchantmentHelper.m_44843_((Enchantment)Enchantments.f_44989_, (ItemStack)stack)) > 0) {
                            abstractarrowentity.m_36735_(k);
                        }
                        if (EnchantmentHelper.m_44843_((Enchantment)Enchantments.f_44990_, (ItemStack)stack) > 0) {
                            abstractarrowentity.m_20254_(100);
                        }
                        if ((event = CraftEventFactory.callEntityShootBowEvent((LivingEntity)playerentity, stack, itemstack, (Entity)abstractarrowentity, playerentity.m_7655_(), f, !flag1)).isCancelled()) {
                            event.getProjectile().remove();
                            return;
                        }
                        flag1 = !event.shouldConsumeItem();
                        stack.m_41622_(1, (LivingEntity)playerentity, player -> player.m_21190_(playerentity.m_7655_()));
                        if (flag1 || playerentity.m_150110_().f_35937_ && (itemstack.m_41720_() == Items.f_42737_ || itemstack.m_41720_() == Items.f_42738_)) {
                            abstractarrowentity.f_36705_ = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }
                        if (event.getProjectile() == ((EntityBridge)abstractarrowentity).bridge$getBukkitEntity() && !worldIn.m_7967_((Entity)abstractarrowentity)) {
                            if (playerentity instanceof ServerPlayerEntityBridge) {
                                ((ServerPlayerEntityBridge)playerentity).bridge$getBukkitEntity().updateInventory();
                            }
                            return;
                        }
                    }
                    worldIn.m_6263_(null, playerentity.m_20185_(), playerentity.m_20186_(), playerentity.m_20189_(), SoundEvents.f_11687_, SoundSource.PLAYERS, 1.0f, 1.0f / (worldIn.m_213780_().m_188501_() * 0.4f + 1.2f) + f * 0.5f);
                    if (!flag1 && !playerentity.m_150110_().f_35937_) {
                        itemstack.m_41774_(1);
                        if (itemstack.m_41619_()) {
                            playerentity.m_150109_().m_36057_(itemstack);
                        }
                    }
                    playerentity.m_36246_(Stats.f_12982_.m_12902_((Object)this));
                }
            }
        }
    }
}

