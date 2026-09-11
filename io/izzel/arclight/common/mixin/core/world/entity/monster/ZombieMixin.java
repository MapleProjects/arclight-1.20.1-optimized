/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.DynamicOps
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnGroupData
 *  net.minecraft.world.entity.monster.Zombie
 *  net.minecraft.world.entity.monster.Zombie$ZombieGroupData
 *  net.minecraft.world.entity.monster.ZombieVillager
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.living.ZombieEvent$SummonAidEvent
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import com.mojang.serialization.DynamicOps;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import io.izzel.arclight.mixin.Eject;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.ZombieEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={net.minecraft.world.entity.monster.Zombie.class})
public abstract class ZombieMixin
extends PathfinderMobMixin {
    @Inject(method={"convertToZombieType"}, at={@At(value="HEAD")})
    private void arclight$transformReason(EntityType<? extends net.minecraft.world.entity.monster.Zombie> entityType, CallbackInfo ci) {
        this.bridge$pushTransformReason(EntityTransformEvent.TransformReason.DROWNED);
        ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.DROWNED);
    }

    @Inject(method={"convertToZombieType"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN")})
    private void arclight$stopConversion(EntityType<? extends net.minecraft.world.entity.monster.Zombie> entityType, CallbackInfo ci, net.minecraft.world.entity.monster.Zombie zombieEntity) {
        if (zombieEntity == null) {
            ((Zombie)((Object)this.bridge$getBukkitEntity())).setConversionTime(-1);
        }
    }

    @Inject(method={"hurt"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/Zombie;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V")})
    private void arclight$spawnWithReason(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir, ServerLevel world, LivingEntity livingEntity, int i, int j, int k, ZombieEvent.SummonAidEvent event, net.minecraft.world.entity.monster.Zombie zombieEntity) {
        ((WorldBridge)world).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.REINFORCEMENTS);
        if (livingEntity != null) {
            ((MobEntityBridge)zombieEntity).bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.REINFORCEMENT_TARGET, true);
        }
    }

    @Redirect(method={"doHurtTarget"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setSecondsOnFire(I)V"))
    private void arclight$entityCombust(Entity entity, int seconds) {
        EntityCombustByEntityEvent event = new EntityCombustByEntityEvent(this.getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity(), seconds);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            ((EntityBridge)entity).bridge$setOnFire(event.getDuration(), false);
        }
    }

    @Eject(method={"killedEntity"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/npc/Villager;convertTo(Lnet/minecraft/world/entity/EntityType;Z)Lnet/minecraft/world/entity/Mob;"))
    private <T extends Mob> T arclight$transform(Villager villagerEntity, EntityType<T> entityType, boolean flag, CallbackInfoReturnable<Boolean> cir) {
        ((WorldBridge)villagerEntity.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.INFECTION);
        ((MobEntityBridge)villagerEntity).bridge$pushTransformReason(EntityTransformEvent.TransformReason.INFECTION);
        Mob t = villagerEntity.m_21406_(entityType, flag);
        if (t == null) {
            cir.setReturnValue((Object)false);
        }
        return (T)t;
    }

    @Inject(method={"finalizeSpawn"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/ServerLevelAccessor;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$mount(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        ((WorldBridge)worldIn.m_6018_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.MOUNT);
    }

    private static ZombieVillager zombifyVillager(ServerLevel level, Villager villager, BlockPos blockPosition, boolean silent, CreatureSpawnEvent.SpawnReason spawnReason) {
        ((WorldBridge)villager.m_9236_()).bridge$pushAddEntityReason(spawnReason);
        ((MobEntityBridge)villager).bridge$pushTransformReason(EntityTransformEvent.TransformReason.INFECTION);
        ZombieVillager zombieVillager = (ZombieVillager)villager.m_21406_(EntityType.f_20530_, false);
        if (zombieVillager != null) {
            zombieVillager.m_6518_((ServerLevelAccessor)level, level.m_6436_(zombieVillager.m_20183_()), MobSpawnType.CONVERSION, (SpawnGroupData)new Zombie.ZombieGroupData(false, true), null);
            zombieVillager.m_34375_(villager.m_7141_());
            zombieVillager.m_34391_((Tag)villager.m_35517_().m_262795_((DynamicOps)NbtOps.f_128958_));
            zombieVillager.m_34411_(villager.m_6616_().m_45388_());
            zombieVillager.m_34373_(villager.m_7809_());
            ForgeEventFactory.onLivingConvert((LivingEntity)villager, (LivingEntity)zombieVillager);
            if (!silent) {
                level.m_5898_(null, 1026, blockPosition, 0);
            }
        }
        return zombieVillager;
    }
}

