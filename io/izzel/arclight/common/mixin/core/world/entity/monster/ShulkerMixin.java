/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.monster.Shulker
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.EntityTeleportEvent$EnderEntity
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Shulker.class})
public abstract class ShulkerMixin
extends PathfinderMobMixin {
    @Shadow
    @Final
    protected static EntityDataAccessor<Byte> f_33401_;

    @Shadow
    @Nullable
    protected abstract Direction m_149810_(BlockPos var1);

    @Shadow
    protected abstract void m_149788_(Direction var1);

    @Overwrite
    protected boolean m_33460_() {
        if (!this.m_21525_() && this.m_6084_()) {
            BlockPos blockpos = this.m_20183_();
            for (int i = 0; i < 5; ++i) {
                BlockPos blockpos1 = blockpos.m_7918_(Mth.m_216287_((RandomSource)this.f_19796_, (int)-8, (int)8), Mth.m_216287_((RandomSource)this.f_19796_, (int)-8, (int)8), Mth.m_216287_((RandomSource)this.f_19796_, (int)-8, (int)8));
                if (blockpos1.m_123342_() <= this.m_9236_().m_141937_() || !this.m_9236_().m_46859_(blockpos1) || !this.m_9236_().m_6857_().m_61937_(blockpos1) || !this.m_9236_().m_45756_((Entity)((Shulker)this), new AABB(blockpos1).m_82406_(1.0E-6))) continue;
                Direction direction = this.m_149810_(blockpos1);
                if (direction != null) {
                    EntityTeleportEvent.EnderEntity event = ForgeEventFactory.onEnderTeleport((LivingEntity)((Shulker)this), (double)blockpos1.m_123341_(), (double)blockpos1.m_123342_(), (double)blockpos1.m_123343_());
                    if (event.isCanceled()) {
                        direction = null;
                    }
                    blockpos1 = BlockPos.m_274561_((double)event.getTargetX(), (double)event.getTargetY(), (double)event.getTargetZ());
                }
                if (direction == null) continue;
                EntityTeleportEvent teleport = new EntityTeleportEvent(this.getBukkitEntity(), this.getBukkitEntity().getLocation(), new Location(((WorldBridge)this.m_9236_()).bridge$getWorld(), blockpos1.m_123341_(), blockpos1.m_123342_(), blockpos1.m_123343_()));
                Bukkit.getPluginManager().callEvent(teleport);
                if (teleport.isCancelled()) {
                    return false;
                }
                Location to = teleport.getTo();
                blockpos1 = BlockPos.m_274561_((double)to.getX(), (double)to.getY(), (double)to.getZ());
                this.m_19877_();
                this.m_149788_(direction);
                this.m_5496_(SoundEvents.f_12418_, 1.0f, 1.0f);
                this.m_6034_((double)blockpos1.m_123341_() + 0.5, blockpos1.m_123342_(), (double)blockpos1.m_123343_() + 0.5);
                this.m_9236_().m_220407_(GameEvent.f_238175_, blockpos, GameEvent.Context.m_223717_((Entity)((Entity)this)));
                this.f_19804_.m_135381_(f_33401_, (Object)0);
                this.m_6710_(null);
                return true;
            }
            return false;
        }
        return false;
    }

    @Inject(method={"hitByShulkerBullet"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$breedCause(CallbackInfo ci) {
        ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.BREEDING);
    }
}

