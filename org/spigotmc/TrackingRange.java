/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Display
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.entity.decoration.Painting
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Ghast
 */
package org.spigotmc;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Ghast;
import org.spigotmc.ActivationRange;
import org.spigotmc.SpigotWorldConfig;

public class TrackingRange {
    public static int getEntityTrackingRange(Entity entity, int defaultRange) {
        if (defaultRange == 0) {
            return defaultRange;
        }
        SpigotWorldConfig config = entity.m_9236_().spigotConfig;
        if (entity instanceof ServerPlayer) {
            return config.playerTrackingRange;
        }
        if (entity.activationType == ActivationRange.ActivationType.MONSTER || entity.activationType == ActivationRange.ActivationType.RAIDER) {
            return config.monsterTrackingRange;
        }
        if (entity instanceof Ghast) {
            if (config.monsterTrackingRange > config.monsterActivationRange) {
                return config.monsterTrackingRange;
            }
            return config.monsterActivationRange;
        }
        if (entity.activationType == ActivationRange.ActivationType.ANIMAL) {
            return config.animalTrackingRange;
        }
        if (entity instanceof ItemFrame || entity instanceof Painting || entity instanceof ItemEntity || entity instanceof ExperienceOrb) {
            return config.miscTrackingRange;
        }
        if (entity instanceof Display) {
            return config.displayTrackingRange;
        }
        return config.otherTrackingRange;
    }
}

