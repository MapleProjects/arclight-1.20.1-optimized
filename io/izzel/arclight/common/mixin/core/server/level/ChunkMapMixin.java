/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixer
 *  javax.annotation.Nullable
 *  net.minecraft.core.HolderGetter
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.level.ChunkHolder
 *  net.minecraft.server.level.ChunkMap
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.progress.ChunkProgressListener
 *  net.minecraft.util.thread.BlockableEventLoop
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.chunk.LightChunkGetter
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.entity.ChunkStatusUpdateListener
 *  net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator
 *  net.minecraft.world.level.levelgen.NoiseGeneratorSettings
 *  net.minecraft.world.level.levelgen.RandomState
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 *  net.minecraft.world.level.storage.LevelStorageSource$LevelStorageAccess
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Invoker
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.server.level;

import com.mojang.datafixers.DataFixer;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkMapBridge;
import io.izzel.arclight.common.mod.util.ArclightCallbackExecutor;
import java.util.concurrent.Executor;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LightChunkGetter;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.entity.ChunkStatusUpdateListener;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.bukkit.craftbukkit.v1_20_R1.generator.CustomChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChunkMap.class})
public abstract class ChunkMapMixin
implements ChunkMapBridge {
    @Shadow
    @Mutable
    public ChunkGenerator f_140136_;
    @Shadow
    @Final
    public ServerLevel f_140133_;
    @Shadow
    @Final
    @Mutable
    private RandomState f_214834_;
    public final ArclightCallbackExecutor callbackExecutor = new ArclightCallbackExecutor();

    @Shadow
    @Nullable
    protected abstract ChunkHolder m_140174_(long var1);

    @Shadow
    protected abstract Iterable<ChunkHolder> m_140416_();

    @Shadow
    protected abstract void m_140421_();

    @Override
    @Invoker(value="tick")
    public abstract void bridge$tick(BooleanSupplier var1);

    @Override
    @Invoker(value="setViewDistance")
    public abstract void bridge$setViewDistance(int var1);

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$updateRandom(ServerLevel p_214836_, LevelStorageSource.LevelStorageAccess p_214837_, DataFixer p_214838_, StructureTemplateManager p_214839_, Executor p_214840_, BlockableEventLoop p_214841_, LightChunkGetter p_214842_, ChunkGenerator p_214843_, ChunkProgressListener p_214844_, ChunkStatusUpdateListener p_214845_, Supplier p_214846_, int p_214847_, boolean p_214848_, CallbackInfo ci) {
        this.bridge$setChunkGenerator(this.f_140136_);
    }

    @Redirect(method={"upgradeChunkTag"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;dimension()Lnet/minecraft/resources/ResourceKey;"))
    private ResourceKey<LevelStem> arclight$useTypeKey(ServerLevel serverWorld) {
        return ((WorldBridge)serverWorld).bridge$getTypeKey();
    }

    @Override
    public ArclightCallbackExecutor bridge$getCallbackExecutor() {
        return this.callbackExecutor;
    }

    @Override
    public ChunkHolder bridge$chunkHolderAt(long chunkPos) {
        return this.m_140174_(chunkPos);
    }

    @Override
    public Iterable<ChunkHolder> bridge$getLoadedChunksIterable() {
        return this.m_140416_();
    }

    @Override
    public void bridge$tickEntityTracker() {
        this.m_140421_();
    }

    @Override
    public void bridge$setChunkGenerator(ChunkGenerator generator) {
        this.f_140136_ = generator;
        if (generator instanceof CustomChunkGenerator) {
            CustomChunkGenerator custom = (CustomChunkGenerator)generator;
            generator = custom.getDelegate();
        }
        if (generator instanceof NoiseBasedChunkGenerator) {
            NoiseBasedChunkGenerator noisebasedchunkgenerator = (NoiseBasedChunkGenerator)generator;
            this.f_214834_ = RandomState.m_255302_((NoiseGeneratorSettings)((NoiseGeneratorSettings)noisebasedchunkgenerator.m_224341_().m_203334_()), (HolderGetter)this.f_140133_.m_9598_().m_255025_(Registries.f_256865_), (long)this.f_140133_.m_7328_());
        } else {
            this.f_214834_ = RandomState.m_255302_((NoiseGeneratorSettings)NoiseGeneratorSettings.m_238396_(), (HolderGetter)this.f_140133_.m_9598_().m_255025_(Registries.f_256865_), (long)this.f_140133_.m_7328_());
        }
    }
}

