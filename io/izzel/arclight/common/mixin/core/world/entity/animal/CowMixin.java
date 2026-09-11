/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.animal.Cow
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.item.Items
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={Cow.class})
public abstract class CowMixin
extends AnimalMixin {
    @Override
    @Overwrite
    public InteractionResult m_6071_(Player playerEntity, InteractionHand hand) {
        ItemStack itemstack = playerEntity.m_21120_(hand);
        if (itemstack.m_41720_() == Items.f_42446_ && !this.m_6162_()) {
            playerEntity.m_5496_(SoundEvents.f_11833_, 1.0f, 1.0f);
            PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent((ServerLevel)playerEntity.m_9236_(), playerEntity, this.m_20183_(), this.m_20183_(), null, itemstack, Items.f_42455_, hand);
            if (event.isCancelled()) {
                return InteractionResult.PASS;
            }
            ItemStack itemstack1 = ItemUtils.m_41813_((ItemStack)itemstack, (Player)playerEntity, (ItemStack)CraftItemStack.asNMSCopy(event.getItemStack()));
            playerEntity.m_21008_(hand, itemstack1);
            return InteractionResult.m_19078_((boolean)this.m_9236_().f_46443_);
        }
        return super.m_6071_(playerEntity, hand);
    }
}

