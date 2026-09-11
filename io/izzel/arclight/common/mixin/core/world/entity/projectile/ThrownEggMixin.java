/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.animal.Chicken
 *  net.minecraft.world.entity.projectile.ThrownEgg
 *  net.minecraft.world.phys.HitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.bukkit.EntityTypeBridge;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.ThrowableProjectileMixin;
import io.izzel.arclight.common.mod.util.Blackhole;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.phys.HitResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Egg;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={ThrownEgg.class})
public abstract class ThrownEggMixin
extends ThrowableProjectileMixin {
    @Override
    @Overwrite
    protected void m_6532_(HitResult result) {
        super.m_6532_(result);
        if (!this.m_9236_().f_46443_) {
            boolean hatching = this.f_19796_.m_188503_(8) == 0;
            int b0 = 1;
            if (this.f_19796_.m_188503_(32) == 0) {
                b0 = 4;
            }
            if (!hatching) {
                b0 = 0;
            }
            org.bukkit.entity.EntityType hatchingType = org.bukkit.entity.EntityType.CHICKEN;
            Entity shooter = this.m_19749_();
            if (shooter instanceof ServerPlayer) {
                PlayerEggThrowEvent event = new PlayerEggThrowEvent(((ServerPlayerEntityBridge)shooter).bridge$getBukkitEntity(), (Egg)((Object)this.getBukkitEntity()), hatching, (byte)b0, hatchingType);
                Bukkit.getPluginManager().callEvent(event);
                b0 = event.getNumHatches();
                hatching = event.isHatching();
                hatchingType = event.getHatchingType();
            }
            if (hatching) {
                for (int i = 0; i < b0; ++i) {
                    EntityType<?> entityType = ((EntityTypeBridge)((Object)hatchingType)).bridge$getHandle();
                    Entity entity = entityType.m_20615_(this.m_9236_());
                    if (entity instanceof Chicken) {
                        Chicken chicken = (Chicken)entity;
                        Blackhole.consume(chicken);
                    }
                    if (entity == null) continue;
                    if (((EntityBridge)entity).bridge$getBukkitEntity() instanceof Ageable) {
                        ((Ageable)((Object)((EntityBridge)entity).bridge$getBukkitEntity())).setBaby();
                    }
                    entity.m_7678_(this.m_20185_(), this.m_20186_(), this.m_20189_(), this.m_146908_(), 0.0f);
                    ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.EGG);
                    this.m_9236_().m_7967_(entity);
                }
            }
            this.m_9236_().m_7605_((Entity)((ThrownEgg)this), (byte)3);
            this.m_146870_();
        }
    }
}

