/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.StreamTagVisitor
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.chunk.storage.RegionFile
 *  net.minecraft.world.level.chunk.storage.RegionFileStorage
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.chunk.storage;

import io.izzel.arclight.common.bridge.core.world.chunk.storage.RegionFileCacheBridge;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StreamTagVisitor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.storage.RegionFile;
import net.minecraft.world.level.chunk.storage.RegionFileStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={RegionFileStorage.class})
public abstract class RegionFileCacheMixin
implements RegionFileCacheBridge {
    private transient boolean arclight$existOnly;

    @Shadow
    protected abstract RegionFile m_63711_(ChunkPos var1) throws IOException;

    private RegionFile loadFile(ChunkPos pos, boolean existsOnly) throws IOException {
        this.arclight$existOnly = existsOnly;
        return this.m_63711_(pos);
    }

    @Inject(method={"getRegionFile"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="NEW", target="net/minecraft/world/level/chunk/storage/RegionFile")})
    private void arclight$retIfSearch(ChunkPos pos, CallbackInfoReturnable<RegionFile> cir, long l, RegionFile rf, Path path) {
        if (this.arclight$existOnly && !Files.exists(path, new LinkOption[0])) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method={"read"}, at={@At(value="HEAD")})
    private void arclight$read(ChunkPos pos, CallbackInfoReturnable<CompoundTag> cir) {
        this.arclight$existOnly = true;
    }

    @Inject(method={"read"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/chunk/storage/RegionFile;getChunkDataInputStream(Lnet/minecraft/world/level/ChunkPos;)Ljava/io/DataInputStream;")})
    private void arclight$retIfNotFound(ChunkPos pos, CallbackInfoReturnable<CompoundTag> cir, RegionFile rf) {
        if (rf == null) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method={"write"}, at={@At(value="HEAD")})
    private void arclight$write(ChunkPos pos, CompoundTag compound, CallbackInfo ci) {
        this.arclight$existOnly = false;
    }

    @Inject(method={"scanChunk"}, at={@At(value="HEAD")})
    private void arclight$scan(CallbackInfo ci) {
        this.arclight$existOnly = true;
    }

    @Inject(method={"scanChunk"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/chunk/storage/RegionFile;getChunkDataInputStream(Lnet/minecraft/world/level/ChunkPos;)Ljava/io/DataInputStream;")})
    private void arclight$retIfNotFound(ChunkPos p_196957_, StreamTagVisitor p_196958_, CallbackInfo ci, RegionFile rf) {
        if (rf == null) {
            ci.cancel();
        }
    }

    public boolean chunkExists(ChunkPos pos) throws IOException {
        RegionFile regionFile = this.loadFile(pos, true);
        return regionFile != null && regionFile.m_63682_(pos);
    }

    @Override
    public boolean bridge$chunkExists(ChunkPos pos) throws IOException {
        return this.chunkExists(pos);
    }
}

