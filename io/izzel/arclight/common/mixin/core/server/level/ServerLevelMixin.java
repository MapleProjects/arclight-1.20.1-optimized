/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  io.izzel.arclight.i18n.ArclightConfig
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Registry
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.level.progress.ChunkProgressListener
 *  net.minecraft.util.ProgressListener
 *  net.minecraft.world.Container
 *  net.minecraft.world.RandomSequences
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.level.CustomSpawner
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.ExplosionDamageCalculator
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.entity.PersistentEntitySectionManager
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 *  net.minecraft.world.level.storage.DerivedLevelData
 *  net.minecraft.world.level.storage.DimensionDataStorage
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 *  net.minecraft.world.level.storage.PrimaryLevelData
 *  net.minecraft.world.level.storage.ServerLevelData
 *  net.minecraft.world.level.storage.WorldData
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.server.level;

import com.google.common.collect.Lists;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.world.ExplosionBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerChunkProviderBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import io.izzel.arclight.common.bridge.core.world.storage.DerivedWorldInfoBridge;
import io.izzel.arclight.common.bridge.core.world.storage.LevelStorageSourceBridge;
import io.izzel.arclight.common.bridge.core.world.storage.MapDataBridge;
import io.izzel.arclight.common.bridge.core.world.storage.WorldInfoBridge;
import io.izzel.arclight.common.mixin.core.world.level.LevelMixin;
import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.server.world.LevelPersistentData;
import io.izzel.arclight.common.mod.server.world.WorldSymlink;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.common.mod.util.DelegateWorldInfo;
import io.izzel.arclight.common.mod.util.DistValidate;
import io.izzel.arclight.i18n.ArclightConfig;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.util.ProgressListener;
import net.minecraft.world.Container;
import net.minecraft.world.RandomSequences;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.PrimaryLevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.GameEvent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.generator.CustomChunkGenerator;
import org.bukkit.craftbukkit.v1_20_R1.util.BlockStateListPopulator;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.WorldUUID;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.world.GenericGameEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.spigotmc.SpigotWorldConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ServerLevel.class})
public abstract class ServerLevelMixin
extends LevelMixin
implements ServerWorldBridge {
    @Shadow
    @Final
    private List<ServerPlayer> f_8546_;
    @Shadow
    @Final
    private ServerChunkCache f_8547_;
    @Shadow
    @Final
    public static BlockPos f_8562_;
    @Shadow
    @Final
    public ServerLevelData f_8549_;
    @Shadow
    @Final
    private PersistentEntitySectionManager<Entity> f_143244_;
    public PrimaryLevelData K;
    public LevelStorageSource.LevelStorageAccess convertable;
    public UUID uuid;
    public ResourceKey<LevelStem> typeKey;
    private transient boolean arclight$force;
    private transient LightningStrikeEvent.Cause arclight$cause;
    private transient CreatureSpawnEvent.SpawnReason arclight$reason;
    private transient boolean arclight$timeSkipCancelled;

    @Shadow
    public abstract boolean m_7967_(Entity var1);

    @Shadow
    public abstract boolean m_8847_(Entity var1);

    @Shadow
    public abstract <T extends ParticleOptions> int m_8767_(T var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15);

    @Shadow
    protected abstract boolean m_8636_(ServerPlayer var1, boolean var2, double var3, double var5, double var7, Packet<?> var9);

    @Override
    @Shadow
    @Nonnull
    public abstract MinecraftServer shadow$m_7654_();

    @Shadow
    public abstract ServerChunkCache m_7726_();

    @Shadow
    protected abstract void m_8804_();

    @Shadow
    public abstract DimensionDataStorage m_8895_();

    @Override
    public ResourceKey<LevelStem> getTypeKey() {
        return this.typeKey;
    }

    public void arclight$constructor(MinecraftServer minecraftServer, Executor backgroundExecutor, LevelStorageSource.LevelStorageAccess levelSave, ServerLevelData worldInfo, ResourceKey<Level> dimension, LevelStem levelStem, ChunkProgressListener statusListener, boolean isDebug, long seed, List<CustomSpawner> specialSpawners, boolean shouldBeTicking, RandomSequences seq) {
        throw new RuntimeException();
    }

    public void arclight$constructor(MinecraftServer minecraftServer, Executor backgroundExecutor, LevelStorageSource.LevelStorageAccess levelSave, PrimaryLevelData worldInfo, ResourceKey<Level> dimension, LevelStem levelStem, ChunkProgressListener statusListener, boolean isDebug, long seed, List<CustomSpawner> specialSpawners, boolean shouldBeTicking, RandomSequences seq, World.Environment env, ChunkGenerator gen, BiomeProvider biomeProvider) {
        this.arclight$constructor(minecraftServer, backgroundExecutor, levelSave, (ServerLevelData)worldInfo, dimension, levelStem, statusListener, isDebug, seed, specialSpawners, shouldBeTicking, seq);
        this.generator = gen;
        this.environment = env;
        this.biomeProvider = biomeProvider;
        if (gen != null) {
            CustomChunkGenerator generator = new CustomChunkGenerator((ServerLevel)this, this.f_8547_.m_8481_(), gen);
            ((ServerChunkProviderBridge)this.f_8547_).bridge$setChunkGenerator(generator);
        }
        this.bridge$getWorld();
    }

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(MinecraftServer minecraftServer, Executor backgroundExecutor, LevelStorageSource.LevelStorageAccess levelSave, ServerLevelData worldInfo, ResourceKey<Level> dimension, LevelStem levelStem, ChunkProgressListener statusListener, boolean isDebug, long seed, List<CustomSpawner> specialSpawners, boolean shouldBeTicking, RandomSequences seq, CallbackInfo ci) {
        Object data;
        this.pvpMode = minecraftServer.m_129799_();
        this.convertable = levelSave;
        ResourceKey<LevelStem> typeKey = ((LevelStorageSourceBridge.LevelStorageAccessBridge)levelSave).bridge$getTypeKey();
        if (typeKey != null) {
            this.typeKey = typeKey;
        } else {
            Registry dimensions = this.shadow$m_7654_().m_206579_().m_175515_(Registries.f_256862_);
            Optional key = dimensions.m_7854_((Object)levelStem);
            if (key.isPresent()) {
                this.typeKey = (ResourceKey)key.get();
            } else {
                ArclightMod.LOGGER.warn("Assign {} to unknown level stem {}", (Object)dimension.m_135782_(), (Object)levelStem);
                this.typeKey = ResourceKey.m_135785_((ResourceKey)Registries.f_256862_, (ResourceLocation)dimension.m_135782_());
            }
        }
        if (worldInfo instanceof PrimaryLevelData) {
            data = (PrimaryLevelData)worldInfo;
            this.K = data;
        } else {
            this.K = DelegateWorldInfo.wrap(worldInfo);
            if (worldInfo instanceof DerivedLevelData) {
                DerivedLevelData data2 = (DerivedLevelData)worldInfo;
                ((DerivedWorldInfoBridge)worldInfo).bridge$setDimType(this.getTypeKey());
                if (ArclightConfig.spec().getCompat().isSymlinkWorld()) {
                    WorldSymlink.create(data2, levelSave.m_197394_(this.m_46472_()).toFile());
                }
            }
        }
        this.spigotConfig = new SpigotWorldConfig(worldInfo.m_5462_());
        this.uuid = WorldUUID.getUUID(levelSave.m_197394_(this.m_46472_()).toFile());
        ((ServerChunkProviderBridge)this.f_8547_).bridge$setViewDistance(this.spigotConfig.viewDistance);
        ((WorldInfoBridge)this.K).bridge$setWorld((ServerLevel)this);
        data = (LevelPersistentData)this.m_8895_().m_164861_(LevelPersistentData::new, () -> new LevelPersistentData(null), "bukkit_pdc");
        this.bridge$getWorld().readBukkitValues((Tag)((LevelPersistentData)((Object)data)).getTag());
    }

    @Inject(method={"saveLevelData"}, at={@At(value="RETURN")})
    private void arclight$savePdc(CallbackInfo ci) {
        LevelPersistentData data = (LevelPersistentData)this.m_8895_().m_164861_(LevelPersistentData::new, () -> new LevelPersistentData(null), "bukkit_pdc");
        data.save(this.world);
    }

    @Inject(method={"gameEvent"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$gameEventEvent(net.minecraft.world.level.gameevent.GameEvent gameEvent, Vec3 pos, GameEvent.Context context, CallbackInfo ci) {
        Entity entity = context.f_223711_();
        int i = gameEvent.m_157827_();
        GenericGameEvent event = new GenericGameEvent(GameEvent.getByKey(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.f_256726_.m_7981_((Object)gameEvent))), new Location(this.getWorld(), pos.m_7096_(), pos.m_7098_(), pos.m_7094_()), entity == null ? null : ((EntityBridge)entity).bridge$getBukkitEntity(), i, !Bukkit.isPrimaryThread());
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    public LevelChunk getChunkIfLoaded(int x, int z) {
        return this.f_8547_.m_62227_(x, z, false);
    }

    public <T extends ParticleOptions> int sendParticles(ServerPlayer sender, T t0, double d0, double d1, double d2, int i, double d3, double d4, double d5, double d6, boolean force) {
        ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(t0, force, d0, d1, d2, (float)d3, (float)d4, (float)d5, (float)d6, i);
        int j = 0;
        for (ServerPlayer entity : this.f_8546_) {
            if (sender != null && !((ServerPlayerEntityBridge)entity).bridge$getBukkitEntity().canSee(((ServerPlayerEntityBridge)sender).bridge$getBukkitEntity()) || !this.m_8636_(entity, force, d0, d1, d2, (Packet<?>)packet)) continue;
            ++j;
        }
        return j;
    }

    @Override
    public LevelStorageSource.LevelStorageAccess bridge$getConvertable() {
        return this.convertable;
    }

    @Inject(method={"tickNonPassenger"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Entity;tick()V")})
    private void arclight$tickPortal(Entity entityIn, CallbackInfo ci) {
        ((EntityBridge)entityIn).bridge$postTick();
    }

    @Inject(method={"tickPassenger"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Entity;rideTick()V")})
    private void arclight$tickPortalPassenger(Entity ridingEntity, Entity passengerEntity, CallbackInfo ci) {
        ((EntityBridge)passengerEntity).bridge$postTick();
    }

    @Inject(method={"tickChunk"}, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    public void arclight$thunder(LevelChunk chunkIn, int randomTickSpeed, CallbackInfo ci) {
        this.bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.LIGHTNING);
    }

    @Redirect(method={"tickChunk"}, at=@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$thunder(ServerLevel serverWorld, Entity entityIn) {
        return this.strikeLightning(entityIn, LightningStrikeEvent.Cause.WEATHER);
    }

    public boolean strikeLightning(Entity entity) {
        return this.strikeLightning(entity, LightningStrikeEvent.Cause.UNKNOWN);
    }

    public boolean strikeLightning(Entity entity, LightningStrikeEvent.Cause cause) {
        LightningStrikeEvent lightning;
        if (this.arclight$cause != null) {
            cause = this.arclight$cause;
            this.arclight$cause = null;
        }
        if (DistValidate.isValid((LevelAccessor)this) && (lightning = CraftEventFactory.callLightningStrikeEvent((LightningStrike)((Object)((EntityBridge)entity).bridge$getBukkitEntity()), cause)).isCancelled()) {
            return false;
        }
        return this.m_7967_(entity);
    }

    @Redirect(method={"tickChunk"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    public boolean arclight$snowForm(ServerLevel serverWorld, BlockPos pos, BlockState state) {
        return CraftEventFactory.handleBlockFormEvent((Level)serverWorld, pos, state, null);
    }

    @Inject(method={"save"}, at={@At(value="JUMP", ordinal=0, opcode=198)})
    private void arclight$worldSaveEvent(ProgressListener progress, boolean flush, boolean skipSave, CallbackInfo ci) {
        if (DistValidate.isValid((LevelAccessor)this)) {
            Bukkit.getPluginManager().callEvent(new WorldSaveEvent(this.bridge$getWorld()));
        }
    }

    @Inject(method={"save"}, at={@At(value="RETURN")})
    private void arclight$saveLevelDat(ProgressListener progress, boolean flush, boolean skipSave, CallbackInfo ci) {
        ServerLevelData serverLevelData = this.f_8549_;
        if (serverLevelData instanceof PrimaryLevelData) {
            PrimaryLevelData worldInfo = (PrimaryLevelData)serverLevelData;
            worldInfo.m_7831_(this.m_6857_().m_61970_());
            worldInfo.m_5917_(this.shadow$m_7654_().m_129901_().m_136307_());
            this.convertable.m_78290_((RegistryAccess)this.shadow$m_7654_().m_206579_(), (WorldData)worldInfo, this.shadow$m_7654_().m_6846_().m_6960_());
        }
    }

    @Inject(method={"unload"}, at={@At(value="HEAD")})
    public void arclight$closeOnChunkUnloading(LevelChunk chunkIn, CallbackInfo ci) {
        for (BlockEntity tileentity : chunkIn.m_62954_().values()) {
            if (!(tileentity instanceof Container)) continue;
            for (HumanEntity h : Lists.newArrayList(((IInventoryBridge)tileentity).getViewers())) {
                if (!(h instanceof CraftHumanEntity)) continue;
                ((CraftHumanEntity)h).getHandle().m_6915_();
            }
        }
    }

    @Redirect(method={"sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/server/level/ServerPlayer;ZDDDLnet/minecraft/network/protocol/Packet;)Z"))
    public boolean arclight$particleVisible(ServerLevel serverWorld, ServerPlayer player, boolean longDistance, double posX, double posY, double posZ, Packet<?> packet) {
        return this.m_8636_(player, this.arclight$force, posX, posY, posZ, packet);
    }

    public <T extends ParticleOptions> int sendParticles(T type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, boolean force) {
        this.arclight$force = force;
        return this.m_8767_(type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed);
    }

    @Override
    public <T extends ParticleOptions> int bridge$sendParticles(T type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed, boolean force) {
        return this.sendParticles(type, posX, posY, posZ, particleCount, xOffset, yOffset, zOffset, speed, force);
    }

    @Override
    public void bridge$pushStrikeLightningCause(LightningStrikeEvent.Cause cause) {
        this.arclight$cause = cause;
    }

    @Override
    public void bridge$strikeLightning(LightningBolt entity, LightningStrikeEvent.Cause cause) {
        this.strikeLightning((Entity)entity, cause);
    }

    @Inject(method={"addEntity"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/entity/PersistentEntitySectionManager;addNewEntity(Lnet/minecraft/world/level/entity/EntityAccess;)Z")})
    private void arclight$addEntityEvent(Entity entityIn, CallbackInfoReturnable<Boolean> cir) {
        CreatureSpawnEvent.SpawnReason reason = this.arclight$reason == null ? CreatureSpawnEvent.SpawnReason.DEFAULT : this.arclight$reason;
        this.arclight$reason = null;
        if (DistValidate.isValid((LevelAccessor)this) && !CraftEventFactory.doEntityAddEventCalling((Level)((ServerLevel)this), entityIn, reason)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"addEntity"}, at={@At(value="RETURN")})
    public void arclight$resetReason(Entity entityIn, CallbackInfoReturnable<Boolean> cir) {
        this.arclight$reason = null;
    }

    @Override
    public void bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason reason) {
        this.arclight$reason = reason;
    }

    @Override
    public CreatureSpawnEvent.SpawnReason bridge$getAddEntityReason() {
        return this.arclight$reason;
    }

    public boolean addFreshEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        this.bridge$pushAddEntityReason(reason);
        return this.m_7967_(entity);
    }

    @Override
    public boolean bridge$addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        return this.addFreshEntity(entity, reason);
    }

    public boolean addWithUUID(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        this.bridge$pushAddEntityReason(reason);
        return this.m_8847_(entity);
    }

    public void addDuringTeleport(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        this.addFreshEntity(entity, reason);
    }

    @Override
    public boolean bridge$addEntitySerialized(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        return this.addWithUUID(entity, reason);
    }

    public boolean tryAddFreshEntityWithPassengers(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        if (entity.m_20199_().map(Entity::m_20148_).anyMatch(arg_0 -> this.f_143244_.m_157550_(arg_0))) {
            return false;
        }
        return this.bridge$addAllEntities(entity, reason);
    }

    @Override
    public boolean bridge$addAllEntitiesSafely(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        return this.tryAddFreshEntityWithPassengers(entity, reason);
    }

    @Inject(method={"explode"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Explosion;interactsWithBlocks()Z")}, locals=LocalCapture.CAPTURE_FAILHARD)
    private void arclight$doExplosion(Entity p_256039_, DamageSource p_255778_, ExplosionDamageCalculator p_256002_, double p_256067_, double p_256370_, double p_256153_, float p_256045_, boolean p_255686_, Level.ExplosionInteraction p_255827_, CallbackInfoReturnable<Explosion> cir, Explosion explosion) {
        if (((ExplosionBridge)explosion).bridge$wasCancelled()) {
            cir.setReturnValue((Object)explosion);
        }
    }

    @Overwrite
    @Nullable
    public MapItemSavedData m_7489_(String mapName) {
        return (MapItemSavedData)this.shadow$m_7654_().m_129783_().m_8895_().m_164858_(nbt -> {
            MapItemSavedData newMap = MapItemSavedData.m_164807_((CompoundTag)nbt);
            ((MapDataBridge)newMap).bridge$setId(mapName);
            MapInitializeEvent event = new MapInitializeEvent(((MapDataBridge)newMap).bridge$getMapView());
            Bukkit.getServer().getPluginManager().callEvent(event);
            return newMap;
        }, mapName);
    }

    @Inject(method={"setMapData"}, at={@At(value="HEAD")})
    private void arclight$mapSetId(String id, MapItemSavedData data, CallbackInfo ci) {
        ((MapDataBridge)data).bridge$setId(id);
    }

    @Inject(method={"blockUpdated"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;updateNeighborsAt(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V")})
    private void arclight$returnIfPopulate(BlockPos pos, Block block, CallbackInfo ci) {
        if (this.populating) {
            ci.cancel();
        }
    }

    @Override
    public BlockEntity getBlockEntity(BlockPos pos, boolean validate) {
        return this.m_7702_(pos);
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V"))
    private void arclight$timeSkip(ServerLevel world, long time) {
        TimeSkipEvent event = new TimeSkipEvent(this.bridge$getWorld(), TimeSkipEvent.SkipReason.NIGHT_SKIP, time - time % 24000L - this.m_46468_());
        Bukkit.getPluginManager().callEvent(event);
        this.arclight$timeSkipCancelled = event.isCancelled();
        if (!event.isCancelled()) {
            world.m_8615_(this.m_46468_() + event.getSkipAmount());
        }
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;wakeUpAllPlayers()V"))
    private void arclight$notWakeIfCancelled(ServerLevel world) {
        if (!this.arclight$timeSkipCancelled) {
            this.m_8804_();
        }
        this.arclight$timeSkipCancelled = false;
    }

    @Override
    public ServerLevel bridge$getMinecraftWorld() {
        return (ServerLevel)this;
    }

    @Overwrite
    public static void m_8617_(ServerLevel world) {
        BlockPos blockpos = f_8562_;
        int i = blockpos.m_123341_();
        int j = blockpos.m_123342_() - 2;
        int k = blockpos.m_123343_();
        BlockStateListPopulator blockList = new BlockStateListPopulator((LevelAccessor)world);
        BlockPos.m_121976_((int)(i - 2), (int)(j + 1), (int)(k - 2), (int)(i + 2), (int)(j + 3), (int)(k + 2)).forEach(pos -> blockList.m_7731_((BlockPos)pos, Blocks.f_50016_.m_49966_(), 3));
        BlockPos.m_121976_((int)(i - 2), (int)j, (int)(k - 2), (int)(i + 2), (int)j, (int)(k + 2)).forEach(pos -> blockList.m_7731_((BlockPos)pos, Blocks.f_50080_.m_49966_(), 3));
        if (!DistValidate.isValid((LevelAccessor)world)) {
            blockList.updateList();
            ArclightCaptures.getEndPortalEntity();
            return;
        }
        CraftWorld bworld = ((WorldBridge)world).bridge$getWorld();
        boolean spawnPortal = ArclightCaptures.getEndPortalSpawn();
        Entity entity = ArclightCaptures.getEndPortalEntity();
        PortalCreateEvent portalEvent = new PortalCreateEvent(blockList.getList(), bworld, entity == null ? null : ((EntityBridge)entity).bridge$getBukkitEntity(), PortalCreateEvent.CreateReason.END_PLATFORM);
        portalEvent.setCancelled(!spawnPortal);
        Bukkit.getPluginManager().callEvent(portalEvent);
        if (!portalEvent.isCancelled()) {
            blockList.updateList();
        }
    }

    @ModifyVariable(method={"tickBlock"}, ordinal=0, argsOnly=true, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/state/BlockState;tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V"))
    private BlockPos arclight$captureTickingBlock(BlockPos pos) {
        ArclightCaptures.captureTickingBlock((ServerLevel)this, pos);
        return pos;
    }

    @ModifyVariable(method={"tickBlock"}, ordinal=0, argsOnly=true, at=@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/level/block/state/BlockState;tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V"))
    private BlockPos arclight$resetTickingBlock(BlockPos pos) {
        ArclightCaptures.resetTickingBlock();
        return pos;
    }

    @ModifyVariable(method={"tickChunk"}, ordinal=0, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/state/BlockState;randomTick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V"))
    private BlockPos arclight$captureRandomTick(BlockPos pos) {
        ArclightCaptures.captureTickingBlock((ServerLevel)this, pos);
        return pos;
    }

    @ModifyVariable(method={"tickChunk"}, ordinal=0, at=@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/level/block/state/BlockState;randomTick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V"))
    private BlockPos arclight$resetRandomTick(BlockPos pos) {
        ArclightCaptures.resetTickingBlock();
        return pos;
    }

    @ModifyVariable(method={"tickNonPassenger"}, argsOnly=true, ordinal=0, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;tick()V"))
    private Entity arclight$captureTickingEntity(Entity entity) {
        ArclightCaptures.captureTickingEntity(entity);
        return entity;
    }

    @ModifyVariable(method={"tickNonPassenger"}, argsOnly=true, ordinal=0, at=@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Entity;tick()V"))
    private Entity arclight$resetTickingEntity(Entity entity) {
        ArclightCaptures.resetTickingEntity();
        return entity;
    }

    @ModifyVariable(method={"tickPassenger"}, argsOnly=true, ordinal=1, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;rideTick()V"))
    private Entity arclight$captureTickingPassenger(Entity entity) {
        ArclightCaptures.captureTickingEntity(entity);
        return entity;
    }

    @ModifyVariable(method={"tickPassenger"}, argsOnly=true, ordinal=1, at=@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Entity;rideTick()V"))
    private Entity arclight$resetTickingPassenger(Entity entity) {
        ArclightCaptures.resetTickingEntity();
        return entity;
    }
}

