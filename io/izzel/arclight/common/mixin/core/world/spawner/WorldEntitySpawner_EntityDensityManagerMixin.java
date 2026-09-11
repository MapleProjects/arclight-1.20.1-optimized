/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobCategory
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.LocalMobCapCalculator
 *  net.minecraft.world.level.NaturalSpawner$SpawnState
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.spawner;

import io.izzel.arclight.common.bridge.core.world.spawner.WorldEntitySpawnerBridge;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LocalMobCapCalculator;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={NaturalSpawner.SpawnState.class})
public abstract class WorldEntitySpawner_EntityDensityManagerMixin
implements WorldEntitySpawnerBridge.EntityDensityManagerBridge {
    @Shadow
    @Final
    private int f_47110_;
    @Shadow
    @Final
    private Object2IntOpenHashMap<MobCategory> f_47111_;
    @Shadow
    @Final
    private LocalMobCapCalculator f_186542_;

    @Shadow
    protected abstract void m_47131_(Mob var1, ChunkAccess var2);

    @Shadow
    protected abstract boolean m_47127_(EntityType<?> var1, BlockPos var2, ChunkAccess var3);

    @Override
    public boolean bridge$canSpawn(EntityType<?> entityType, BlockPos pos, ChunkAccess chunk) {
        return this.m_47127_(entityType, pos, chunk);
    }

    @Override
    public void bridge$updateDensity(Mob mobEntity, ChunkAccess chunk) {
        this.m_47131_(mobEntity, chunk);
    }

    @Override
    public boolean bridge$canSpawn(MobCategory classification, ChunkPos pos, int limit) {
        int i = limit * this.f_47110_ / 289;
        return this.f_47111_.getInt((Object)classification) >= i ? false : this.f_186542_.m_186504_(classification, pos);
    }
}

