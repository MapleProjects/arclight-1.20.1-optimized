/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 */
package io.izzel.arclight.common.bridge.core.world.server;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.weather.LightningStrikeEvent;

public interface ServerWorldBridge
extends WorldBridge {
    public <T extends ParticleOptions> int bridge$sendParticles(T var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15, boolean var17);

    public void bridge$pushStrikeLightningCause(LightningStrikeEvent.Cause var1);

    public void bridge$strikeLightning(LightningBolt var1, LightningStrikeEvent.Cause var2);

    public BlockEntity bridge$getTileEntity(BlockPos var1);

    public boolean bridge$addEntitySerialized(Entity var1, CreatureSpawnEvent.SpawnReason var2);

    public boolean bridge$addAllEntities(Entity var1, CreatureSpawnEvent.SpawnReason var2);

    public boolean bridge$addAllEntitiesSafely(Entity var1, CreatureSpawnEvent.SpawnReason var2);

    public LevelStorageSource.LevelStorageAccess bridge$getConvertable();
}

