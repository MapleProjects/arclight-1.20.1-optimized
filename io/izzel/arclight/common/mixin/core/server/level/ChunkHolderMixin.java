/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  it.unimi.dsi.fastutil.shorts.ShortSet
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.server.level.ChunkHolder$ChunkLoadingFailure
 *  net.minecraft.server.level.ChunkLevel
 *  net.minecraft.server.level.ChunkMap
 *  net.minecraft.server.level.FullChunkStatus
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkStatus
 *  net.minecraft.world.level.chunk.LevelChunk
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.server.level;

import com.mojang.datafixers.util.Either;
import io.izzel.arclight.common.bridge.core.world.chunk.ChunkBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkHolderBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkMapBridge;
import io.izzel.arclight.common.mod.ArclightMod;
import it.unimi.dsi.fastutil.shorts.ShortSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkLevel;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.FullChunkStatus;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ChunkHolder.class})
public abstract class ChunkHolderMixin
implements ChunkHolderBridge {
    @Shadow
    public int f_140006_;
    @Shadow
    @Final
    ChunkPos f_140009_;
    @Shadow
    @Final
    private ShortSet[] f_140011_;

    @Shadow
    public abstract CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> m_140047_(ChunkStatus var1);

    @Override
    @Accessor(value="oldTicketLevel")
    public abstract int bridge$getOldTicketLevel();

    public LevelChunk getFullChunkNow() {
        if (!ChunkLevel.m_287264_((int)this.f_140006_).m_287205_(FullChunkStatus.FULL)) {
            return null;
        }
        return this.getFullChunkNowUnchecked();
    }

    public LevelChunk getFullChunkNowUnchecked() {
        CompletableFuture<Either<ChunkAccess, ChunkHolder.ChunkLoadingFailure>> statusFuture = this.m_140047_(ChunkStatus.f_62326_);
        Either either = statusFuture.getNow(null);
        return either == null ? null : (LevelChunk)either.left().orElse(null);
    }

    @Override
    public LevelChunk bridge$getFullChunk() {
        return this.getFullChunkNow();
    }

    @Override
    public LevelChunk bridge$getFullChunkUnchecked() {
        return this.getFullChunkNowUnchecked();
    }

    @Inject(method={"blockChanged"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="FIELD", ordinal=0, target="Lnet/minecraft/server/level/ChunkHolder;changedBlocksPerSection:[Lit/unimi/dsi/fastutil/shorts/ShortSet;")})
    private void arclight$outOfBound(BlockPos pos, CallbackInfo ci, LevelChunk chunk, int i) {
        if (i < 0 || i >= this.f_140011_.length) {
            ci.cancel();
        }
    }

    @Inject(method={"updateFutures"}, at={@At(value="JUMP", opcode=153, ordinal=0)}, locals=LocalCapture.CAPTURE_FAILHARD)
    public void arclight$onChunkUnload(ChunkMap chunkManager, Executor executor, CallbackInfo ci, ChunkStatus chunkStatus, ChunkStatus chunkStatus1, boolean flag, boolean flag1, FullChunkStatus locationType, FullChunkStatus locationType1) {
        if (locationType.m_287205_(FullChunkStatus.FULL) && !locationType1.m_287205_(FullChunkStatus.FULL)) {
            ((CompletableFuture)this.m_140047_(ChunkStatus.f_62326_).thenAccept(either -> {
                LevelChunk chunk = either.left().orElse(null);
                if (chunk != null) {
                    ((ChunkMapBridge)chunkManager).bridge$getCallbackExecutor().execute(() -> {
                        chunk.m_8092_(true);
                        ((ChunkBridge)chunk).bridge$unloadCallback();
                    });
                }
            })).exceptionally(throwable -> {
                ArclightMod.LOGGER.fatal("Failed to schedule unload callback for chunk " + String.valueOf(this.f_140009_), throwable);
                return null;
            });
            ((ChunkMapBridge)chunkManager).bridge$getCallbackExecutor().run();
        }
    }

    @Inject(method={"updateFutures"}, at={@At(value="RETURN")}, locals=LocalCapture.CAPTURE_FAILHARD)
    public void arclight$onChunkLoad(ChunkMap chunkManager, Executor executor, CallbackInfo ci, ChunkStatus chunkStatus, ChunkStatus chunkStatus1, boolean flag, boolean flag1, FullChunkStatus locationType, FullChunkStatus locationType1) {
        if (!locationType.m_287205_(FullChunkStatus.FULL) && locationType1.m_287205_(FullChunkStatus.FULL)) {
            ((CompletableFuture)this.m_140047_(ChunkStatus.f_62326_).thenAccept(either -> {
                LevelChunk chunk = either.left().orElse(null);
                if (chunk != null) {
                    ((ChunkMapBridge)chunkManager).bridge$getCallbackExecutor().execute(((ChunkBridge)chunk)::bridge$loadCallback);
                }
            })).exceptionally(throwable -> {
                ArclightMod.LOGGER.fatal("Failed to schedule load callback for chunk " + String.valueOf(this.f_140009_), throwable);
                return null;
            });
            ((ChunkMapBridge)chunkManager).bridge$getCallbackExecutor().run();
        }
    }
}

