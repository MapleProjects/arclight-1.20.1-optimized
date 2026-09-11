/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Sheep
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.DyeItem
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.mixin.Eject;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.SheepDyeWoolEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={DyeItem.class})
public class DyeItemMixin {
    @Shadow
    @Final
    private DyeColor f_41077_;

    @Eject(method={"interactLivingEntity"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Sheep;setColor(Lnet/minecraft/world/item/DyeColor;)V"))
    private void arclight$sheepDyeWool(net.minecraft.world.entity.animal.Sheep sheepEntity, DyeColor color, CallbackInfoReturnable<Boolean> cir, ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
        byte bColor = (byte)this.f_41077_.m_41060_();
        SheepDyeWoolEvent event = new SheepDyeWoolEvent((Sheep)((Object)((LivingEntityBridge)target).bridge$getBukkitEntity()), org.bukkit.DyeColor.getByWoolData(bColor), ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity());
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        } else {
            sheepEntity.m_29855_(DyeColor.m_41053_((int)event.getColor().getWoolData()));
        }
    }
}

