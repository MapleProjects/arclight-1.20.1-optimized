/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.decoration.LeashFenceKnotEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.LeadItem
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.Hanging;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LeadItem.class})
public class LeadItemMixin {
    private static InteractionHand arclight$hand;

    @Inject(method={"useOn"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/item/LeadItem;bindPlayerMobs(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/InteractionResult;")})
    private void arclight$captureHand(UseOnContext p_42834_, CallbackInfoReturnable<InteractionResult> cir) {
        arclight$hand = p_42834_.m_43724_();
    }

    @Inject(method={"useOn"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/item/LeadItem;bindPlayerMobs(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/InteractionResult;")})
    private void arclight$resetHand(UseOnContext p_42834_, CallbackInfoReturnable<InteractionResult> cir) {
        arclight$hand = p_42834_.m_43724_();
    }

    @Overwrite
    public static InteractionResult m_42829_(Player player, Level worldIn, BlockPos fence) {
        LeashFenceKnotEntity leashknotentity = null;
        boolean flag = false;
        double d0 = 7.0;
        int i = fence.m_123341_();
        int j = fence.m_123342_();
        int k = fence.m_123343_();
        for (Mob mobentity : worldIn.m_45976_(Mob.class, new AABB((double)i - 7.0, (double)j - 7.0, (double)k - 7.0, (double)i + 7.0, (double)j + 7.0, (double)k + 7.0))) {
            if (mobentity.m_21524_() != player) continue;
            if (leashknotentity == null) {
                leashknotentity = LeashFenceKnotEntity.m_31844_((Level)worldIn, (BlockPos)fence);
                HangingPlaceEvent event = new HangingPlaceEvent((Hanging)((Object)((EntityBridge)leashknotentity).bridge$getBukkitEntity()), player != null ? (org.bukkit.entity.Player)((Object)((PlayerEntityBridge)player).bridge$getBukkitEntity()) : null, CraftBlock.at((LevelAccessor)worldIn, fence), BlockFace.SELF, CraftEquipmentSlot.getHand(arclight$hand));
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) {
                    leashknotentity.m_146870_();
                    return InteractionResult.PASS;
                }
            }
            if (CraftEventFactory.callPlayerLeashEntityEvent(mobentity, (Entity)leashknotentity, player, arclight$hand).isCancelled()) continue;
            mobentity.m_21463_((Entity)leashknotentity, true);
            flag = true;
        }
        return flag ? InteractionResult.SUCCESS : InteractionResult.PASS;
    }
}

