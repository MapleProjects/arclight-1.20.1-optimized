/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.decoration.HangingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.HangingEntityItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Hanging;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={HangingEntityItem.class})
public class HangingEntityItemMixin {
    @Inject(method={"useOn"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/HangingEntity;playPlacementSound()V")})
    public void arclight$hangingPlace(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, BlockPos blockPos, Direction direction, BlockPos blockPos1, Player playerEntity, ItemStack itemStack, Level world, HangingEntity hangingEntity) {
        if (!DistValidate.isValid(context)) {
            return;
        }
        org.bukkit.entity.Player who = context.m_43723_() == null ? null : (org.bukkit.entity.Player)((Object)((PlayerEntityBridge)context.m_43723_()).bridge$getBukkitEntity());
        CraftBlock blockClicked = CraftBlock.at((LevelAccessor)world, blockPos);
        BlockFace blockFace = CraftBlock.notchToBlockFace(direction);
        HangingPlaceEvent event = new HangingPlaceEvent((Hanging)((Object)((EntityBridge)hangingEntity).bridge$getBukkitEntity()), who, blockClicked, blockFace, CraftEquipmentSlot.getHand(context.m_43724_()), CraftItemStack.asBukkitCopy(itemStack));
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)InteractionResult.FAIL);
        }
    }
}

