/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundAddEntityPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Bucketable
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.network.datasync.SynchedEntityDataBridge;
import java.util.Optional;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={Bucketable.class})
public interface BucketableMixin {
    @Overwrite
    public static <T extends LivingEntity> Optional<InteractionResult> m_148828_(Player player, InteractionHand hand, LivingEntity livingEntity) {
        LivingEntity entity = livingEntity;
        ItemStack itemstack = player.m_21120_(hand);
        if (itemstack.m_41720_() == Items.f_42447_ && entity.m_6084_()) {
            ItemStack itemstack1 = ((Bucketable)entity).m_28282_();
            ((Bucketable)entity).m_6872_(itemstack1);
            PlayerBucketEntityEvent event = CraftEventFactory.callPlayerFishBucketEvent(entity, player, itemstack, itemstack1, hand);
            itemstack1 = CraftItemStack.asNMSCopy(event.getEntityBucket());
            if (event.isCancelled()) {
                player.f_36096_.m_150429_();
                ((ServerPlayer)player).f_8906_.m_9829_((Packet)new ClientboundAddEntityPacket((Entity)entity));
                ((SynchedEntityDataBridge)livingEntity.m_20088_()).bridge$refresh((ServerPlayer)player);
                return Optional.of(InteractionResult.FAIL);
            }
            entity.m_5496_(((Bucketable)entity).m_142623_(), 1.0f, 1.0f);
            ItemStack itemstack2 = ItemUtils.m_41817_((ItemStack)itemstack, (Player)player, (ItemStack)itemstack1, (boolean)false);
            player.m_21008_(hand, itemstack2);
            Level level = entity.m_9236_();
            if (!level.f_46443_) {
                CriteriaTriggers.f_10576_.m_38772_((ServerPlayer)player, itemstack1);
            }
            entity.m_146870_();
            return Optional.of(InteractionResult.m_19078_((boolean)level.f_46443_));
        }
        return Optional.empty();
    }
}

