/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.chunk.ChunkAccess
 */
package io.izzel.arclight.common.bridge.core.world.spawner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;

public interface WorldEntitySpawnerBridge {

    public static interface EntityDensityManagerBridge {
        public void bridge$updateDensity(Mob var1, ChunkAccess var2);

        public boolean bridge$canSpawn(MobCategory var1, ChunkPos var2, int var3);

        public boolean bridge$canSpawn(EntityType<?> var1, BlockPos var2, ChunkAccess var3);
    }
}

