/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractArrow$Pickup
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.EntityHitResult
 *  net.minecraft.world.phys.HitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerInventoryBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.ProjectileMixin;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftItem;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AbstractArrow.class})
public abstract class AbstractArrowMixin
extends ProjectileMixin {
    @Shadow
    public boolean f_36703_;
    @Shadow
    public int f_36706_;
    @Shadow
    public AbstractArrow.Pickup f_36705_;

    @Shadow
    public abstract boolean m_36797_();

    @Shadow
    protected abstract ItemStack m_7941_();

    @Redirect(method={"tick"}, at=@At(value="INVOKE", opcode=181, target="Lnet/minecraft/world/entity/projectile/AbstractArrow;onHit(Lnet/minecraft/world/phys/HitResult;)V"))
    private void arclight$hitEvent(AbstractArrow abstractArrow, HitResult hitResult) {
        this.preOnHit(hitResult);
    }

    @Redirect(method={"onHitEntity"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V"))
    private void arclight$fireShot(Entity entity, int seconds, EntityHitResult result) {
        EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(this.getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity(), seconds);
        Bukkit.getPluginManager().callEvent(combustEvent);
        if (!combustEvent.isCancelled()) {
            ((EntityBridge)entity).bridge$setOnFire(combustEvent.getDuration(), false);
        }
    }

    @Overwrite
    public void m_6123_(Player playerEntity) {
        if (!this.m_9236_().f_46443_ && (this.f_36703_ || this.m_36797_()) && this.f_36706_ <= 0) {
            ItemStack itemstack = this.m_7941_();
            if (this.f_36705_ == AbstractArrow.Pickup.ALLOWED && !itemstack.m_41619_() && ((PlayerInventoryBridge)playerEntity.m_150109_()).bridge$canHold(itemstack) > 0) {
                ItemEntity item = new ItemEntity(this.m_9236_(), this.m_20185_(), this.m_20186_(), this.m_20189_(), itemstack);
                PlayerPickupArrowEvent event = new PlayerPickupArrowEvent((org.bukkit.entity.Player)((ServerPlayerEntityBridge)playerEntity).bridge$getBukkitEntity(), (Item)new CraftItem((CraftServer)Bukkit.getServer(), (Entity)((AbstractArrow)this), item), (org.bukkit.entity.AbstractArrow)((Object)this.getBukkitEntity()));
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) {
                    return;
                }
                itemstack = item.m_32055_();
            }
            if (this.f_36705_ == AbstractArrow.Pickup.ALLOWED && playerEntity.m_150109_().m_36054_(itemstack) || this.f_36705_ == AbstractArrow.Pickup.CREATIVE_ONLY && playerEntity.m_150110_().f_35937_) {
                playerEntity.m_7938_((Entity)((AbstractArrow)this), 1);
                this.m_146870_();
            }
        }
    }

    @Inject(method={"setOwner"}, at={@At(value="HEAD")})
    private void arclight$setShooter(Entity entityIn, CallbackInfo ci) {
        this.projectileSource = entityIn == null ? null : (ProjectileSource)((Object)((EntityBridge)entityIn).bridge$getBukkitEntity());
    }
}

