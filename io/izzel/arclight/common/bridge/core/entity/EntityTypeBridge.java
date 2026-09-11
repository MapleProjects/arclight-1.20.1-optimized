/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.MobSpawnType
 */
package io.izzel.arclight.common.bridge.core.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import org.bukkit.event.entity.CreatureSpawnEvent;

public interface EntityTypeBridge<T extends Entity> {
    public T bridge$spawnCreature(ServerLevel var1, BlockPos var2, MobSpawnType var3, CreatureSpawnEvent.SpawnReason var4);
}

