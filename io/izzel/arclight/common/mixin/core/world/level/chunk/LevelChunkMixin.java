/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.chunk.LevelChunk$PostLoadProcessor
 *  net.minecraft.world.level.chunk.LevelChunkSection
 *  net.minecraft.world.level.chunk.ProtoChunk
 *  net.minecraft.world.level.chunk.UpgradeData
 *  net.minecraft.world.level.levelgen.blending.BlendingData
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.ticks.LevelChunkTicks
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.chunk;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.chunk.ChunkAccessBridge;
import io.izzel.arclight.common.bridge.core.world.chunk.ChunkBridge;
import io.izzel.arclight.common.mixin.core.world.level.chunk.ChunkAccessMixin;
import io.izzel.arclight.common.mod.util.DistValidate;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.chunk.UpgradeData;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.ticks.LevelChunkTicks;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_20_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.DirtyCraftPersistentDataContainer;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.generator.BlockPopulator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LevelChunk.class})
public abstract class LevelChunkMixin
extends ChunkAccessMixin
implements ChunkBridge {
    @Shadow
    @Final
    public Level f_62776_;
    public boolean mustNotSave;
    public boolean needsDecoration;
    private transient boolean arclight$doPlace;
    public ServerLevel r;

    @Shadow
    @Nullable
    public abstract BlockState m_6978_(BlockPos var1, BlockState var2, boolean var3);

    @Inject(method={"<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/chunk/UpgradeData;Lnet/minecraft/world/ticks/LevelChunkTicks;Lnet/minecraft/world/ticks/LevelChunkTicks;J[Lnet/minecraft/world/level/chunk/LevelChunkSection;Lnet/minecraft/world/level/chunk/LevelChunk$PostLoadProcessor;Lnet/minecraft/world/level/levelgen/blending/BlendingData;)V"}, at={@At(value="RETURN")})
    private void arclight$init(Level worldIn, ChunkPos p_196855_, UpgradeData p_196856_, LevelChunkTicks<Block> p_196857_, LevelChunkTicks<Fluid> p_196858_, long p_196859_, @Nullable LevelChunkSection[] p_196860_, @Nullable LevelChunk.PostLoadProcessor p_196861_, @Nullable BlendingData p_196862_, CallbackInfo ci) {
        if (DistValidate.isValid((LevelAccessor)worldIn)) {
            this.r = (ServerLevel)worldIn;
        }
    }

    @Inject(method={"<init>(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ProtoChunk;Lnet/minecraft/world/level/chunk/LevelChunk$PostLoadProcessor;)V"}, at={@At(value="RETURN")})
    private void arclight$init(ServerLevel p_196850_, ProtoChunk protoChunk, @Nullable LevelChunk.PostLoadProcessor p_196852_, CallbackInfo ci) {
        this.needsDecoration = true;
        this.persistentDataContainer = (DirtyCraftPersistentDataContainer)((ChunkAccessBridge)protoChunk).bridge$getPersistentDataContainer();
    }

    @Inject(method={"removeBlockEntity"}, at={@At(value="INVOKE_ASSIGN", remap=false, target="Ljava/util/Map;remove(Ljava/lang/Object;)Ljava/lang/Object;")})
    private void arclight$remove(BlockPos pos, CallbackInfo ci) {
        if (!this.f_187609_.isEmpty()) {
            this.f_187609_.remove(pos);
        }
    }

    public Chunk getBukkitChunk() {
        return new CraftChunk((LevelChunk)this);
    }

    @Override
    public Chunk bridge$getBukkitChunk() {
        return this.getBukkitChunk();
    }

    @Override
    public CraftPersistentDataContainer bridge$getPersistentContainer() {
        return this.persistentDataContainer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public BlockState setBlockState(BlockPos pos, BlockState state, boolean isMoving, boolean doPlace) {
        this.arclight$doPlace = doPlace;
        try {
            BlockState blockState = this.m_6978_(pos, state, isMoving);
            return blockState;
        }
        finally {
            this.arclight$doPlace = true;
        }
    }

    @Override
    public BlockState bridge$setType(BlockPos pos, BlockState state, boolean isMoving, boolean doPlace) {
        return this.setBlockState(pos, state, isMoving, doPlace);
    }

    @Override
    public boolean bridge$isMustNotSave() {
        return this.mustNotSave;
    }

    @Override
    public void bridge$setMustNotSave(boolean mustNotSave) {
        this.mustNotSave = mustNotSave;
    }

    @Override
    public boolean bridge$isNeedsDecoration() {
        return this.needsDecoration;
    }

    @Override
    public void bridge$loadCallback() {
        this.loadCallback();
    }

    @Override
    public void bridge$unloadCallback() {
        this.unloadCallback();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void loadCallback() {
        Server server = Bukkit.getServer();
        if (server != null) {
            CraftChunk bukkitChunk = new CraftChunk((LevelChunk)this);
            server.getPluginManager().callEvent(new ChunkLoadEvent(bukkitChunk, this.needsDecoration));
            if (this.needsDecoration) {
                this.needsDecoration = false;
                Random random = new Random();
                random.setSeed(((ServerLevel)this.f_62776_).m_7328_());
                long xRand = random.nextLong() / 2L * 2L + 1L;
                long zRand = random.nextLong() / 2L * 2L + 1L;
                random.setSeed((long)this.f_187604_.f_45578_ * xRand + (long)this.f_187604_.f_45579_ * zRand ^ ((ServerLevel)this.f_62776_).m_7328_());
                CraftWorld world = ((WorldBridge)this.f_62776_).bridge$getWorld();
                if (world != null) {
                    ((WorldBridge)this.f_62776_).bridge$setPopulating(true);
                    try {
                        for (BlockPopulator populator : world.getPopulators()) {
                            populator.populate(world, random, bukkitChunk);
                        }
                    }
                    finally {
                        ((WorldBridge)this.f_62776_).bridge$setPopulating(false);
                    }
                }
                server.getPluginManager().callEvent(new ChunkPopulateEvent(bukkitChunk));
            }
        }
    }

    public void unloadCallback() {
        Server server = Bukkit.getServer();
        CraftChunk bukkitChunk = new CraftChunk((LevelChunk)this);
        ChunkUnloadEvent unloadEvent = new ChunkUnloadEvent(bukkitChunk, this.m_6344_());
        server.getPluginManager().callEvent(unloadEvent);
        this.mustNotSave = !unloadEvent.isSaveChunk();
    }

    @Redirect(method={"setBlockState"}, at=@At(value="FIELD", ordinal=1, target="Lnet/minecraft/world/level/Level;isClientSide:Z"))
    public boolean arclight$redirectIsRemote(Level world) {
        return world.f_46443_ && this.arclight$doPlace;
    }

    @Override
    public boolean m_6344_() {
        return super.m_6344_() && !this.mustNotSave;
    }
}

