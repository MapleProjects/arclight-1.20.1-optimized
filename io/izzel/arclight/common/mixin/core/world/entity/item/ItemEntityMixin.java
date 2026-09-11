/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.item;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerInventoryBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.network.datasync.SynchedEntityDataBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import java.util.UUID;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ItemEntity.class})
public abstract class ItemEntityMixin
extends EntityMixin {
    @Shadow
    @Final
    private static EntityDataAccessor<ItemStack> f_31984_;
    @Shadow
    public int f_31986_;
    @Shadow
    public UUID f_265881_;

    @Shadow
    public abstract ItemStack m_32055_();

    @Inject(method={"merge(Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/world/item/ItemStack;)V"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$itemMerge(ItemEntity from, ItemStack stack1, ItemEntity to, ItemStack stack2, CallbackInfo ci) {
        if (!CraftEventFactory.callItemMergeEvent(to, from)) {
            ci.cancel();
        }
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/item/ItemEntity;markHurt()V")})
    private void arclight$damageNonLiving(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (CraftEventFactory.handleNonLivingEntityDamageEvent((Entity)((ItemEntity)this), source, amount)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Overwrite
    public void m_6123_(Player entity) {
        if (!this.m_9236_().f_46443_) {
            if (this.f_31986_ > 0) {
                return;
            }
            ItemStack itemstack = this.m_32055_();
            int i = itemstack.m_41613_();
            int hook = ForgeEventFactory.onItemPickup((ItemEntity)((ItemEntity)this), (Player)entity);
            if (hook < 0) {
                return;
            }
            int canHold = ((PlayerInventoryBridge)entity.m_150109_()).bridge$canHold(itemstack);
            int remaining = itemstack.m_41613_() - canHold;
            if (this.f_31986_ <= 0 && canHold > 0) {
                itemstack.m_41764_(canHold);
                PlayerPickupItemEvent playerEvent = new PlayerPickupItemEvent(((ServerPlayerEntityBridge)entity).bridge$getBukkitEntity(), (Item)((Object)this.getBukkitEntity()), remaining);
                playerEvent.setCancelled(!((PlayerEntityBridge)entity).bridge$canPickUpLoot());
                Bukkit.getPluginManager().callEvent(playerEvent);
                if (playerEvent.isCancelled()) {
                    itemstack.m_41764_(canHold + remaining);
                    return;
                }
                EntityPickupItemEvent entityEvent = new EntityPickupItemEvent(((LivingEntityBridge)entity).bridge$getBukkitEntity(), (Item)((Object)this.getBukkitEntity()), remaining);
                entityEvent.setCancelled(!((PlayerEntityBridge)entity).bridge$canPickUpLoot());
                Bukkit.getPluginManager().callEvent(entityEvent);
                if (entityEvent.isCancelled()) {
                    itemstack.m_41764_(canHold + remaining);
                    return;
                }
                ItemStack current = this.m_32055_();
                if (!itemstack.equals(current)) {
                    itemstack = current;
                } else {
                    itemstack.m_41764_(canHold + remaining);
                }
                this.f_31986_ = 0;
            } else if (this.f_31986_ == 0 && hook != 1) {
                this.f_31986_ = -1;
            }
            ItemStack copy = itemstack.m_41777_();
            if (this.f_31986_ == 0 && (this.f_265881_ == null || this.f_265881_.equals(entity.m_20148_())) && (hook == 1 || entity.m_150109_().m_36054_(itemstack))) {
                copy.m_41764_(copy.m_41613_() - itemstack.m_41613_());
                ForgeEventFactory.firePlayerItemPickupEvent((Player)entity, (ItemEntity)((ItemEntity)this), (ItemStack)copy);
                entity.m_7938_((Entity)((ItemEntity)this), i);
                if (itemstack.m_41619_()) {
                    this.m_146870_();
                    itemstack.m_41764_(i);
                }
                entity.m_6278_(Stats.f_12984_.m_12902_((Object)itemstack.m_41720_()), i);
                entity.m_21053_((ItemEntity)this);
            }
        }
    }

    @Inject(method={"setItem"}, at={@At(value="RETURN")})
    private void arclight$markDirty(ItemStack stack, CallbackInfo ci) {
        ((SynchedEntityDataBridge)this.m_20088_()).bridge$markDirty(f_31984_);
    }

    @Redirect(method={"merge(Lnet/minecraft/world/entity/item/ItemEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/item/ItemEntity;setItem(Lnet/minecraft/world/item/ItemStack;)V"))
    private static void arclight$setNonEmpty(ItemEntity itemEntity, ItemStack stack) {
        if (!stack.m_41619_()) {
            itemEntity.m_32045_(stack);
        }
    }

    @Redirect(method={"mergeWithNeighbours"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/phys/AABB;inflate(DDD)Lnet/minecraft/world/phys/AABB;"))
    private AABB arclight$mergeRadius(AABB instance, double pX, double pY, double pZ) {
        double radius = ((WorldBridge)this.m_9236_()).bridge$spigotConfig().itemMerge;
        return instance.m_82400_(radius);
    }
}

