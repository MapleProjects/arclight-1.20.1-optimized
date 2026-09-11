/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.server.level.ChunkLevel
 *  net.minecraft.server.level.ChunkMap
 *  net.minecraft.server.level.DistanceManager
 *  net.minecraft.server.level.FullChunkStatus
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ThreadedLevelLightEngine
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameRules$BooleanValue
 *  net.minecraft.world.level.GameRules$Key
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.storage.LevelData
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.server.level;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkHolderBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkMapBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerChunkProviderBridge;
import io.izzel.arclight.common.bridge.core.world.server.TicketManagerBridge;
import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkLevel;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ThreadedLevelLightEngine;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.storage.LevelData;
import org.bukkit.entity.SpawnCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ServerChunkCache.class})
public abstract class ServerChunkCacheMixin
implements ServerChunkProviderBridge {
    @Shadow
    @Final
    ThreadedLevelLightEngine f_8331_;
    @Shadow
    @Final
    public ChunkMap f_8325_;
    @Shadow
    @Final
    public ServerLevel f_8329_;
    @Shadow
    @Final
    private DistanceManager f_8327_;

    @Shadow
    public abstract void m_8419_(boolean var1);

    @Shadow
    protected abstract void m_8488_();

    @Shadow
    @Nullable
    protected abstract ChunkHolder m_8364_(long var1);

    @Override
    @Invoker(value="runDistanceManagerUpdates")
    public abstract boolean bridge$tickDistanceManager();

    @Override
    @Accessor(value="lightEngine")
    public abstract ThreadedLevelLightEngine bridge$getLightManager();

    public boolean isChunkLoaded(int chunkX, int chunkZ) {
        ChunkHolder chunk = ((ChunkMapBridge)this.f_8325_).bridge$chunkHolderAt(ChunkPos.m_45589_((int)chunkX, (int)chunkZ));
        return chunk != null && ((ChunkHolderBridge)chunk).bridge$getFullChunk() != null;
    }

    public LevelChunk getChunkUnchecked(int chunkX, int chunkZ) {
        ChunkHolder chunk = ((ChunkMapBridge)this.f_8325_).bridge$chunkHolderAt(ChunkPos.m_45589_((int)chunkX, (int)chunkZ));
        if (chunk == null) {
            return null;
        }
        return ((ChunkHolderBridge)chunk).bridge$getFullChunkUnchecked();
    }

    @Override
    public boolean bridge$isChunkLoaded(int x, int z) {
        return this.isChunkLoaded(x, z);
    }

    @Override
    public void bridge$setChunkGenerator(ChunkGenerator chunkGenerator) {
        ((ChunkMapBridge)this.f_8325_).bridge$setChunkGenerator(chunkGenerator);
    }

    @Override
    public void bridge$setViewDistance(int viewDistance) {
        ((ChunkMapBridge)this.f_8325_).bridge$setViewDistance(viewDistance);
    }

    @ModifyVariable(method={"getChunkFutureMainThread"}, index=4, at=@At(value="HEAD"))
    private boolean arclight$skipIfUnloading(boolean flag, int chunkX, int chunkZ) {
        if (flag) {
            ChunkHolder chunkholder = this.m_8364_(ChunkPos.m_45589_((int)chunkX, (int)chunkZ));
            if (chunkholder != null) {
                FullChunkStatus chunkStatus = ChunkLevel.m_287264_((int)((ChunkHolderBridge)chunkholder).bridge$getOldTicketLevel());
                FullChunkStatus currentStatus = ChunkLevel.m_287264_((int)chunkholder.m_140093_());
                return !chunkStatus.m_287205_(FullChunkStatus.FULL) || currentStatus.m_287205_(FullChunkStatus.FULL);
            }
            return true;
        }
        return false;
    }

    @Redirect(method={"tickChunks"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"))
    private boolean arclight$noPlayer(GameRules gameRules, GameRules.Key<GameRules.BooleanValue> key) {
        return gameRules.m_46207_(key) && !this.f_8329_.m_6907_().isEmpty();
    }

    @Redirect(method={"tickChunks"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/storage/LevelData;getGameTime()J"))
    private long arclight$ticksPer(LevelData worldInfo) {
        long gameTime = worldInfo.m_6793_();
        long ticksPer = ((WorldBridge)this.f_8329_).bridge$ticksPerSpawnCategory().getLong((Object)SpawnCategory.ANIMAL);
        return ticksPer != 0L && gameTime % ticksPer == 0L ? 0L : 1L;
    }

    public void close(boolean save) throws IOException {
        if (save) {
            this.m_8419_(true);
        }
        this.f_8331_.close();
        this.f_8325_.close();
    }

    public void purgeUnload() {
        this.f_8329_.m_46473_().m_6180_("purge");
        ((TicketManagerBridge)this.f_8327_).bridge$tick();
        this.bridge$tickDistanceManager();
        this.f_8329_.m_46473_().m_6182_("unload");
        ((ChunkMapBridge)this.f_8325_).bridge$tick(() -> true);
        this.f_8329_.m_46473_().m_7238_();
        this.m_8488_();
    }

    @Override
    public void bridge$close(boolean save) throws IOException {
        this.close(save);
    }

    @Override
    public void bridge$purgeUnload() {
        this.purgeUnload();
    }

    @Redirect(method={"chunkAbsent"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ChunkHolder;getTicketLevel()I"))
    public int arclight$useOldTicketLevel(ChunkHolder chunkHolder) {
        return ((ChunkHolderBridge)chunkHolder).bridge$getOldTicketLevel();
    }
}

