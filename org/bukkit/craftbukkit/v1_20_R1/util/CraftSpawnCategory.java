/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.MobCategory
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import net.minecraft.world.entity.MobCategory;
import org.bukkit.entity.SpawnCategory;

public class CraftSpawnCategory {
    public static boolean isValidForLimits(SpawnCategory spawnCategory) {
        return spawnCategory != null && spawnCategory != SpawnCategory.MISC;
    }

    public static String getConfigNameSpawnLimit(SpawnCategory spawnCategory) {
        return switch (spawnCategory) {
            case SpawnCategory.MONSTER -> "spawn-limits.monsters";
            case SpawnCategory.ANIMAL -> "spawn-limits.animals";
            case SpawnCategory.WATER_ANIMAL -> "spawn-limits.water-animals";
            case SpawnCategory.WATER_AMBIENT -> "spawn-limits.water-ambient";
            case SpawnCategory.WATER_UNDERGROUND_CREATURE -> "spawn-limits.water-underground-creature";
            case SpawnCategory.AMBIENT -> "spawn-limits.ambient";
            case SpawnCategory.AXOLOTL -> "spawn-limits.axolotls";
            default -> throw new UnsupportedOperationException("Unknown Config value " + (Object)((Object)spawnCategory) + " for spawn-limits");
        };
    }

    public static String getConfigNameTicksPerSpawn(SpawnCategory spawnCategory) {
        return switch (spawnCategory) {
            case SpawnCategory.MONSTER -> "ticks-per.monster-spawns";
            case SpawnCategory.ANIMAL -> "ticks-per.animal-spawns";
            case SpawnCategory.WATER_ANIMAL -> "ticks-per.water-spawns";
            case SpawnCategory.WATER_AMBIENT -> "ticks-per.water-ambient-spawns";
            case SpawnCategory.WATER_UNDERGROUND_CREATURE -> "ticks-per.water-underground-creature-spawns";
            case SpawnCategory.AMBIENT -> "ticks-per.ambient-spawns";
            case SpawnCategory.AXOLOTL -> "ticks-per.axolotl-spawns";
            default -> throw new UnsupportedOperationException("Unknown Config value " + (Object)((Object)spawnCategory) + " for ticks-per");
        };
    }

    public static long getDefaultTicksPerSpawn(SpawnCategory spawnCategory) {
        return switch (spawnCategory) {
            case SpawnCategory.MONSTER, SpawnCategory.WATER_ANIMAL, SpawnCategory.WATER_AMBIENT, SpawnCategory.WATER_UNDERGROUND_CREATURE, SpawnCategory.AMBIENT, SpawnCategory.AXOLOTL -> 1L;
            case SpawnCategory.ANIMAL -> 400L;
            default -> throw new UnsupportedOperationException("Unknown Config value " + (Object)((Object)spawnCategory) + " for ticks-per");
        };
    }

    public static SpawnCategory toBukkit(MobCategory enumCreatureType) {
        return switch (enumCreatureType) {
            case MobCategory.MONSTER -> SpawnCategory.MONSTER;
            case MobCategory.CREATURE -> SpawnCategory.ANIMAL;
            case MobCategory.AMBIENT -> SpawnCategory.AMBIENT;
            case MobCategory.AXOLOTLS -> SpawnCategory.AXOLOTL;
            case MobCategory.WATER_CREATURE -> SpawnCategory.WATER_ANIMAL;
            case MobCategory.WATER_AMBIENT -> SpawnCategory.WATER_AMBIENT;
            case MobCategory.UNDERGROUND_WATER_CREATURE -> SpawnCategory.WATER_UNDERGROUND_CREATURE;
            case MobCategory.MISC -> SpawnCategory.MISC;
            default -> throw new UnsupportedOperationException("Unknown EnumCreatureType " + enumCreatureType + " for SpawnCategory");
        };
    }

    public static MobCategory toNMS(SpawnCategory spawnCategory) {
        return switch (spawnCategory) {
            case SpawnCategory.MONSTER -> MobCategory.MONSTER;
            case SpawnCategory.ANIMAL -> MobCategory.CREATURE;
            case SpawnCategory.AMBIENT -> MobCategory.AMBIENT;
            case SpawnCategory.AXOLOTL -> MobCategory.AXOLOTLS;
            case SpawnCategory.WATER_ANIMAL -> MobCategory.WATER_CREATURE;
            case SpawnCategory.WATER_AMBIENT -> MobCategory.WATER_AMBIENT;
            case SpawnCategory.WATER_UNDERGROUND_CREATURE -> MobCategory.UNDERGROUND_WATER_CREATURE;
            case SpawnCategory.MISC -> MobCategory.MISC;
            default -> throw new UnsupportedOperationException("Unknown SpawnCategory " + (Object)((Object)spawnCategory) + " for EnumCreatureType");
        };
    }
}

