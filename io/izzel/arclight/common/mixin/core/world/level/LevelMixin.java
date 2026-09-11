/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.profiling.ProfilerFiller
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelWriter
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.storage.LevelData
 *  net.minecraft.world.level.storage.ServerLevelData
 *  net.minecraft.world.level.storage.WritableLevelData
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.border.WorldBorderBridge;
import io.izzel.arclight.common.bridge.core.world.level.levelgen.ChunkGeneratorBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerChunkProviderBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import io.izzel.arclight.common.mod.server.ArclightServer;
import io.izzel.arclight.common.mod.server.world.WrappedWorlds;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.generator.CraftWorldInfo;
import org.bukkit.craftbukkit.v1_20_R1.generator.CustomChunkGenerator;
import org.bukkit.craftbukkit.v1_20_R1.generator.CustomWorldChunkManager;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftSpawnCategory;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.generator.BiomeProvider;
import org.jetbrains.annotations.Nullable;
import org.spigotmc.SpigotWorldConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Level.class})
public abstract class LevelMixin
implements WorldBridge,
LevelWriter {
    @Shadow
    @Final
    private WorldBorder f_46447_;
    protected CraftWorld world;
    public boolean pvpMode;
    public boolean keepSpawnInMemory = true;
    public final Object2LongOpenHashMap<SpawnCategory> ticksPerSpawnCategory = new Object2LongOpenHashMap();
    public boolean populating;
    public org.bukkit.generator.ChunkGenerator generator;
    protected World.Environment environment;
    protected BiomeProvider biomeProvider;
    public SpigotWorldConfig spigotConfig;
    private static BlockPos lastPhysicsProblem;
    public boolean preventPoiUpdated = false;

    @Shadow
    @javax.annotation.Nullable
    public BlockEntity m_7702_(BlockPos pos) {
        return null;
    }

    @Shadow
    public abstract BlockState m_8055_(BlockPos var1);

    @Shadow
    public abstract WorldBorder m_6857_();

    @Shadow
    public abstract long m_46468_();

    @Shadow
    public abstract MinecraftServer shadow$m_7654_();

    @Shadow
    public abstract LevelData m_6106_();

    @Shadow
    public abstract ResourceKey<Level> m_46472_();

    @Shadow(remap=false)
    public abstract void markAndNotifyBlock(BlockPos var1, @Nullable LevelChunk var2, BlockState var3, BlockState var4, int var5, int var6);

    @Shadow
    public abstract DimensionType m_6042_();

    @Accessor(value="thread")
    public abstract Thread arclight$getMainThread();

    public void arclight$constructor(WritableLevelData worldInfo, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimensionType, Supplier<ProfilerFiller> profiler, boolean isRemote, boolean isDebug, long seed, int maxNeighborUpdate) {
        throw new RuntimeException();
    }

    public void arclight$constructor(WritableLevelData worldInfo, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimensionType, Supplier<ProfilerFiller> profiler, boolean isRemote, boolean isDebug, long seed, int maxNeighborUpdate, org.bukkit.generator.ChunkGenerator gen, BiomeProvider biomeProvider, World.Environment env) {
        this.arclight$constructor(worldInfo, dimension, registryAccess, dimensionType, profiler, isRemote, isDebug, seed, maxNeighborUpdate);
        this.generator = gen;
        this.environment = env;
        this.biomeProvider = biomeProvider;
        this.bridge$getWorld();
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(WritableLevelData info, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimType, Supplier<ProfilerFiller> profiler, boolean isRemote, boolean isDebug, long seed, int maxNeighborUpdates, CallbackInfo ci) {
        ((WorldBorderBridge)this.f_46447_).bridge$setWorld((Level)this);
        for (SpawnCategory spawnCategory : SpawnCategory.values()) {
            if (!CraftSpawnCategory.isValidForLimits(spawnCategory)) continue;
            this.ticksPerSpawnCategory.put((Object)spawnCategory, (long)this.getCraftServer().getTicksPerSpawns(spawnCategory));
        }
    }

    @Override
    public void bridge$setLastPhysicsProblem(BlockPos pos) {
        lastPhysicsProblem = pos;
    }

    @Override
    public Object2LongOpenHashMap<SpawnCategory> bridge$ticksPerSpawnCategory() {
        return this.ticksPerSpawnCategory;
    }

    public abstract ResourceKey<LevelStem> getTypeKey();

    @Override
    public ResourceKey<LevelStem> bridge$getTypeKey() {
        return this.getTypeKey();
    }

    @Override
    public SpigotWorldConfig bridge$spigotConfig() {
        return this.spigotConfig;
    }

    @Inject(method={"setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void arclight$hooks(BlockPos pos, BlockState newState, int flags, CallbackInfoReturnable<Boolean> cir) {
        if (!this.processCaptures(pos, newState, flags)) {
            cir.setReturnValue((Object)false);
        }
    }

    private boolean processCaptures(BlockPos pos, BlockState newState, int flags) {
        Entity entityChangeBlock = ArclightCaptures.getEntityChangeBlock();
        return entityChangeBlock == null || CraftEventFactory.callEntityChangeBlockEvent(entityChangeBlock, pos, newState);
    }

    @Inject(method={"markAndNotifyBlock"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/block/state/BlockState;updateNeighbourShapes(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;II)V")})
    private void arclight$callBlockPhysics(BlockPos pos, LevelChunk chunk, BlockState blockstate, BlockState state, int flags, int recursionLeft, CallbackInfo ci) {
        try {
            if (this.world != null) {
                BlockPhysicsEvent event = new BlockPhysicsEvent(CraftBlock.at((LevelAccessor)this, pos), CraftBlockData.fromData(state));
                Bukkit.getPluginManager().callEvent(event);
                if (event.isCancelled()) {
                    ci.cancel();
                }
            }
        }
        catch (StackOverflowError e) {
            lastPhysicsProblem = pos;
        }
    }

    @Inject(method={"markAndNotifyBlock"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;onBlockStateChange(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V")})
    private void arclight$preventPoiUpdate(BlockPos p_46605_, LevelChunk levelchunk, BlockState blockstate, BlockState p_46606_, int p_46607_, int p_46608_, CallbackInfo ci) {
        if (this.preventPoiUpdated) {
            ci.cancel();
        }
    }

    public void notifyAndUpdatePhysics(BlockPos blockposition, LevelChunk chunk, BlockState oldBlock, BlockState newBlock, BlockState actualBlock, int i, int j) {
        this.markAndNotifyBlock(blockposition, chunk, oldBlock, newBlock, i, j);
    }

    public CraftServer getCraftServer() {
        return (CraftServer)Bukkit.getServer();
    }

    public CraftWorld getWorld() {
        if (this.world == null) {
            Optional<Field> delegate = WrappedWorlds.getDelegate(this.getClass());
            if (delegate.isPresent()) {
                try {
                    return ((WorldBridge)delegate.get().get(this)).bridge$getWorld();
                }
                catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this.environment == null) {
                this.environment = ArclightServer.getEnvironment(this.getTypeKey());
            }
            if (this.generator == null) {
                LevelMixin levelMixin;
                this.generator = this.getCraftServer().getGenerator(((ServerLevelData)this.m_6106_()).m_5462_());
                if (this.generator != null && (levelMixin = this) instanceof ServerLevel) {
                    ServerLevel serverWorld = (ServerLevel)levelMixin;
                    CraftWorldInfo worldInfo = new CraftWorldInfo((ServerLevelData)this.m_6106_(), ((ServerWorldBridge)((Object)this)).bridge$getConvertable(), this.environment, this.m_6042_());
                    if (this.biomeProvider == null && this.generator != null) {
                        this.biomeProvider = this.generator.getDefaultBiomeProvider(worldInfo);
                    }
                    ChunkGenerator generator = serverWorld.m_7726_().m_8481_();
                    if (this.biomeProvider != null) {
                        CustomWorldChunkManager biomeSource = new CustomWorldChunkManager(worldInfo, this.biomeProvider, (Registry<Biome>)serverWorld.m_9598_().m_175515_(Registries.f_256952_));
                        ((ChunkGeneratorBridge)generator).bridge$setBiomeSource(biomeSource);
                    }
                    CustomChunkGenerator gen = new CustomChunkGenerator(serverWorld, generator, this.generator);
                    ((ServerChunkProviderBridge)serverWorld.m_7726_()).bridge$setChunkGenerator(gen);
                }
            }
            this.world = new CraftWorld((ServerLevel)this, this.generator, this.biomeProvider, this.environment);
            this.getCraftServer().addWorld(this.world);
        }
        return this.world;
    }

    public BlockEntity getBlockEntity(BlockPos pos, boolean validate) {
        return this.m_7702_(pos);
    }

    @Override
    public BlockEntity bridge$getTileEntity(BlockPos pos, boolean validate) {
        return this.getBlockEntity(pos, validate);
    }

    @Override
    public CraftServer bridge$getServer() {
        return (CraftServer)Bukkit.getServer();
    }

    @Override
    public CraftWorld bridge$getWorld() {
        return this.getWorld();
    }

    @Override
    public boolean bridge$isPvpMode() {
        return this.pvpMode;
    }

    @Override
    public boolean bridge$isKeepSpawnInMemory() {
        return this.keepSpawnInMemory;
    }

    @Override
    public boolean bridge$isPopulating() {
        return this.populating;
    }

    @Override
    public void bridge$setPopulating(boolean populating) {
        this.populating = populating;
    }

    @Override
    public org.bukkit.generator.ChunkGenerator bridge$getGenerator() {
        return this.generator;
    }

    @Override
    public ServerLevel bridge$getMinecraftWorld() {
        return this.getWorld().getHandle();
    }

    @Override
    public boolean bridge$addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        if (this.getWorld().getHandle() != this) {
            return ((WorldBridge)this.getWorld().getHandle()).bridge$addEntity(entity, reason);
        }
        this.bridge$pushAddEntityReason(reason);
        return this.m_7967_(entity);
    }

    @Override
    public void bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason reason) {
        if (this.getWorld().getHandle() != this) {
            ((WorldBridge)this.getWorld().getHandle()).bridge$pushAddEntityReason(reason);
        }
    }

    @Override
    public CreatureSpawnEvent.SpawnReason bridge$getAddEntityReason() {
        if (this.getWorld().getHandle() != this) {
            return ((WorldBridge)this.getWorld().getHandle()).bridge$getAddEntityReason();
        }
        return null;
    }
}

