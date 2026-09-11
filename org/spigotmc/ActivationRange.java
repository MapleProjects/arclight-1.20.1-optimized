/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.ambient.AmbientCreature
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Sheep
 *  net.minecraft.world.entity.boss.EnderDragonPart
 *  net.minecraft.world.entity.boss.enderdragon.EndCrystal
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.boss.wither.WitherBoss
 *  net.minecraft.world.entity.item.PrimedTnt
 *  net.minecraft.world.entity.monster.Creeper
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.Slime
 *  net.minecraft.world.entity.npc.Villager
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.entity.projectile.AbstractHurtingProjectile
 *  net.minecraft.world.entity.projectile.FireworkRocketEntity
 *  net.minecraft.world.entity.projectile.ThrowableProjectile
 *  net.minecraft.world.entity.projectile.ThrownTrident
 *  net.minecraft.world.entity.raid.Raider
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.AABB
 */
package org.spigotmc;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.bukkit.craftbukkit.v1_20_R1.SpigotTimings;
import org.spigotmc.SpigotWorldConfig;

public class ActivationRange {
    static AABB maxBB = new AABB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    public static ActivationType initializeEntityActivationType(Entity entity) {
        if (entity instanceof Raider) {
            return ActivationType.RAIDER;
        }
        if (entity instanceof Monster || entity instanceof Slime) {
            return ActivationType.MONSTER;
        }
        if (entity instanceof PathfinderMob || entity instanceof AmbientCreature) {
            return ActivationType.ANIMAL;
        }
        return ActivationType.MISC;
    }

    public static boolean initializeEntityActivationState(Entity entity, SpigotWorldConfig config) {
        return entity.activationType == ActivationType.MISC && config.miscActivationRange == 0 || entity.activationType == ActivationType.RAIDER && config.raiderActivationRange == 0 || entity.activationType == ActivationType.ANIMAL && config.animalActivationRange == 0 || entity.activationType == ActivationType.MONSTER && config.monsterActivationRange == 0 || entity instanceof Player || entity instanceof ThrowableProjectile || entity instanceof EnderDragon || entity instanceof EnderDragonPart || entity instanceof WitherBoss || entity instanceof AbstractHurtingProjectile || entity instanceof LightningBolt || entity instanceof PrimedTnt || entity instanceof EndCrystal || entity instanceof FireworkRocketEntity || entity instanceof ThrownTrident;
    }

    public static void activateEntities(Level world) {
        SpigotTimings.entityActivationCheckTimer.startTiming();
        int miscActivationRange = world.spigotConfig.miscActivationRange;
        int raiderActivationRange = world.spigotConfig.raiderActivationRange;
        int animalActivationRange = world.spigotConfig.animalActivationRange;
        int monsterActivationRange = world.spigotConfig.monsterActivationRange;
        int maxRange = Math.max(monsterActivationRange, animalActivationRange);
        maxRange = Math.max(maxRange, raiderActivationRange);
        maxRange = Math.max(maxRange, miscActivationRange);
        maxRange = Math.min((world.spigotConfig.simulationDistance << 4) - 8, maxRange);
        for (Player player : world.m_6907_()) {
            player.activatedTick = MinecraftServer.currentTick;
            if (world.spigotConfig.ignoreSpectatorActivation && player.m_5833_()) continue;
            maxBB = player.m_20191_().m_82377_((double)maxRange, 256.0, (double)maxRange);
            ActivationType.MISC.boundingBox = player.m_20191_().m_82377_((double)miscActivationRange, 256.0, (double)miscActivationRange);
            ActivationType.RAIDER.boundingBox = player.m_20191_().m_82377_((double)raiderActivationRange, 256.0, (double)raiderActivationRange);
            ActivationType.ANIMAL.boundingBox = player.m_20191_().m_82377_((double)animalActivationRange, 256.0, (double)animalActivationRange);
            ActivationType.MONSTER.boundingBox = player.m_20191_().m_82377_((double)monsterActivationRange, 256.0, (double)monsterActivationRange);
            world.m_142646_().m_142232_(maxBB, ActivationRange::activateEntity);
        }
        SpigotTimings.entityActivationCheckTimer.stopTiming();
    }

    private static void activateEntity(Entity entity) {
        if ((long)MinecraftServer.currentTick > entity.activatedTick) {
            if (entity.defaultActivationState) {
                entity.activatedTick = MinecraftServer.currentTick;
                return;
            }
            if (entity.activationType.boundingBox.m_82381_(entity.m_20191_())) {
                entity.activatedTick = MinecraftServer.currentTick;
            }
        }
    }

    public static boolean checkEntityImmunities(Entity entity) {
        if (entity.f_19798_ || entity.m_20094_() > 0) {
            return true;
        }
        if (!(entity instanceof AbstractArrow) ? !entity.m_20096_() || !entity.f_19823_.isEmpty() || entity.m_20159_() : !((AbstractArrow)entity).f_36703_) {
            return true;
        }
        if (entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            if (living.f_20916_ > 0 || living.f_20945_.size() > 0) {
                return true;
            }
            if (entity instanceof PathfinderMob && ((PathfinderMob)entity).m_5448_() != null) {
                return true;
            }
            if (entity instanceof Villager && ((Villager)entity).m_35506_()) {
                return true;
            }
            if (entity instanceof Animal) {
                Animal animal = (Animal)entity;
                if (animal.m_6162_() || animal.m_27593_()) {
                    return true;
                }
                if (entity instanceof Sheep && ((Sheep)entity).m_29875_()) {
                    return true;
                }
            }
            if (entity instanceof Creeper && ((Creeper)entity).m_32311_()) {
                return true;
            }
        }
        return entity instanceof ExperienceOrb;
    }

    public static boolean checkIfActive(Entity entity) {
        boolean isActive;
        SpigotTimings.checkIfActiveTimer.startTiming();
        if (entity instanceof FireworkRocketEntity) {
            SpigotTimings.checkIfActiveTimer.stopTiming();
            return true;
        }
        boolean bl = isActive = entity.activatedTick >= (long)MinecraftServer.currentTick || entity.defaultActivationState;
        if (!isActive) {
            if (((long)MinecraftServer.currentTick - entity.activatedTick - 1L) % 20L == 0L) {
                if (ActivationRange.checkEntityImmunities(entity)) {
                    entity.activatedTick = MinecraftServer.currentTick + 20;
                }
                isActive = true;
            }
        } else if (!entity.defaultActivationState && entity.f_19797_ % 4 == 0 && !ActivationRange.checkEntityImmunities(entity)) {
            isActive = false;
        }
        SpigotTimings.checkIfActiveTimer.stopTiming();
        return isActive;
    }

    public static enum ActivationType {
        MONSTER,
        ANIMAL,
        RAIDER,
        MISC;

        public AABB boundingBox = new AABB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }
}

