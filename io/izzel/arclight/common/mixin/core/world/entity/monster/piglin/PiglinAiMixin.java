/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.memory.MemoryModuleType
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.piglin.Piglin
 *  net.minecraft.world.entity.monster.piglin.PiglinAi
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster.piglin;

import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.monster.piglin.PiglinBridge;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={PiglinAi.class})
public abstract class PiglinAiMixin {
    @Shadow
    private static void m_35006_(Piglin p_234531_0_) {
    }

    @Shadow
    private static ItemStack m_34822_(ItemEntity p_234465_0_) {
        return null;
    }

    @Shadow
    private static void m_34932_(Piglin p_241427_0_, ItemStack p_241427_1_) {
    }

    @Shadow
    private static void m_34938_(LivingEntity p_234501_0_) {
    }

    @Shadow
    private static boolean m_35018_(Piglin p_234538_0_) {
        return false;
    }

    @Shadow
    private static void m_35014_(Piglin p_234536_0_) {
    }

    @Shadow
    private static void m_34952_(Piglin p_234498_0_, ItemStack p_234498_1_) {
    }

    @Shadow
    protected static boolean m_149965_(ItemStack p_149966_) {
        return false;
    }

    @Shadow
    private static boolean m_149969_(ItemStack p_149970_) {
        return false;
    }

    @Shadow
    private static boolean m_149967_(ItemStack p_149968_) {
        return false;
    }

    @Shadow
    private static List<ItemStack> m_34996_(Piglin p_34997_) {
        return null;
    }

    @Shadow
    private static void m_34860_(Piglin p_34861_, List<ItemStack> p_34862_) {
    }

    @Overwrite
    protected static void m_34843_(Piglin piglinEntity, ItemEntity itemEntity) {
        ItemStack itemstack;
        PiglinAiMixin.m_35006_(piglinEntity);
        if (itemEntity.m_32055_().m_41720_() == Items.f_42587_ && !CraftEventFactory.callEntityPickupItemEvent((Entity)piglinEntity, itemEntity, 0, false).isCancelled()) {
            piglinEntity.m_7938_((Entity)itemEntity, itemEntity.m_32055_().m_41613_());
            itemstack = itemEntity.m_32055_();
            itemEntity.m_146870_();
        } else if (!CraftEventFactory.callEntityPickupItemEvent((Entity)piglinEntity, itemEntity, itemEntity.m_32055_().m_41613_() - 1, false).isCancelled()) {
            piglinEntity.m_7938_((Entity)itemEntity, 1);
            itemstack = PiglinAiMixin.m_34822_(itemEntity);
        } else {
            return;
        }
        if (PiglinAiMixin.isLovedByPiglin(itemstack, piglinEntity)) {
            piglinEntity.m_6274_().m_21936_(MemoryModuleType.f_26337_);
            PiglinAiMixin.m_34932_(piglinEntity, itemstack);
            PiglinAiMixin.m_34938_((LivingEntity)piglinEntity);
        } else if (PiglinAiMixin.m_149969_(itemstack) && !PiglinAiMixin.m_35018_(piglinEntity)) {
            PiglinAiMixin.m_35014_(piglinEntity);
        } else {
            boolean flag;
            ((MobEntityBridge)piglinEntity).bridge$captureItemDrop(itemEntity);
            boolean bl = flag = !piglinEntity.m_255207_(itemstack).equals(ItemStack.f_41583_);
            if (!flag) {
                PiglinAiMixin.m_34952_(piglinEntity, itemstack);
            }
        }
    }

    private static boolean isLovedByPiglin(ItemStack itemstack, Piglin piglin) {
        return PiglinAiMixin.m_149965_(itemstack) || ((PiglinBridge)piglin).bridge$getInterestItems().contains(itemstack.m_41720_()) || ((PiglinBridge)piglin).bridge$getAllowedBarterItems().contains(itemstack.m_41720_());
    }

    private static boolean isBarterItem(ItemStack itemstack, Piglin piglin) {
        return PiglinAiMixin.m_149967_(itemstack) || ((PiglinBridge)piglin).bridge$getAllowedBarterItems().contains(itemstack.m_41720_());
    }

    @Redirect(method={"stopHoldingOffHandItem"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/item/ItemStack;isPiglinCurrency()Z"))
    private static boolean arclight$customBarter(ItemStack stack, Piglin piglin) {
        return PiglinAiMixin.isBarterItem(stack, piglin);
    }

    @Redirect(method={"stopHoldingOffHandItem"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/piglin/PiglinAi;throwItems(Lnet/minecraft/world/entity/monster/piglin/Piglin;Ljava/util/List;)V"))
    private static void arclight$barterEvent(Piglin piglin, List<ItemStack> items) {
        ItemStack stack = piglin.m_21120_(InteractionHand.OFF_HAND);
        PiglinBarterEvent event = CraftEventFactory.callPiglinBarterEvent(piglin, PiglinAiMixin.m_34996_(piglin), stack);
        if (!event.isCancelled()) {
            PiglinAiMixin.m_34860_(piglin, event.getOutcome().stream().map(CraftItemStack::asNMSCopy).collect(Collectors.toList()));
        }
    }

    @Redirect(method={"stopHoldingOffHandItem"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/piglin/PiglinAi;isLovedItem(Lnet/minecraft/world/item/ItemStack;)Z"))
    private static boolean arclight$customLove(ItemStack stack, Piglin piglin) {
        return PiglinAiMixin.isLovedByPiglin(stack, piglin);
    }

    @Redirect(method={"wantsToPickup"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/item/ItemStack;isPiglinCurrency()Z"))
    private static boolean arclight$customBanter2(ItemStack stack, Piglin piglin) {
        return PiglinAiMixin.isBarterItem(stack, piglin);
    }

    @Redirect(method={"canAdmire"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/item/ItemStack;isPiglinCurrency()Z"))
    private static boolean arclight$customBanter3(ItemStack stack, Piglin piglin) {
        return PiglinAiMixin.isBarterItem(stack, piglin);
    }

    @Redirect(method={"isNotHoldingLovedItemInOffHand"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/entity/monster/piglin/PiglinAi;isLovedItem(Lnet/minecraft/world/item/ItemStack;)Z"))
    private static boolean arclight$customLove2(ItemStack stack, Piglin piglin) {
        return PiglinAiMixin.isLovedByPiglin(stack, piglin);
    }
}

