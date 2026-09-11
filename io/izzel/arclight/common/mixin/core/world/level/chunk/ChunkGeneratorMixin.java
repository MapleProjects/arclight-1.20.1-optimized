/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.core.HolderSet
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.SectionPos
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.StructureManager
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeSource
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.ChunkGenerator
 *  net.minecraft.world.level.levelgen.LegacyRandomSource
 *  net.minecraft.world.level.levelgen.RandomState
 *  net.minecraft.world.level.levelgen.WorldgenRandom
 *  net.minecraft.world.level.levelgen.structure.BoundingBox
 *  net.minecraft.world.level.levelgen.structure.Structure
 *  net.minecraft.world.level.levelgen.structure.StructureSet$StructureSelectionEntry
 *  net.minecraft.world.level.levelgen.structure.StructureStart
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.chunk;

import io.izzel.arclight.common.bridge.core.world.IWorldBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.level.levelgen.ChunkGeneratorBridge;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.SectionPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.generator.CraftLimitedRegion;
import org.bukkit.craftbukkit.v1_20_R1.generator.structure.CraftStructure;
import org.bukkit.craftbukkit.v1_20_R1.util.RandomSourceWrapper;
import org.bukkit.event.world.AsyncStructureSpawnEvent;
import org.bukkit.generator.BlockPopulator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ChunkGenerator.class})
public abstract class ChunkGeneratorMixin
implements ChunkGeneratorBridge {
    @Shadow
    @Final
    @Mutable
    protected BiomeSource f_62137_;

    @Shadow
    public abstract void m_213609_(WorldGenLevel var1, ChunkAccess var2, StructureManager var3);

    @Inject(method={"applyBiomeDecoration"}, at={@At(value="RETURN")})
    private void arclight$addBukkitDecoration(WorldGenLevel level, ChunkAccess chunkAccess, StructureManager manager, CallbackInfo ci) {
        this.addDecorations(level, chunkAccess, manager);
    }

    /*
     * WARNING - void declaration
     */
    @Inject(method={"tryGenerateStructure"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/StructureManager;setStartForStructure(Lnet/minecraft/core/SectionPos;Lnet/minecraft/world/level/levelgen/structure/Structure;Lnet/minecraft/world/level/levelgen/structure/StructureStart;Lnet/minecraft/world/level/chunk/StructureAccess;)V")})
    private void arclight$structureSpawn(StructureSet.StructureSelectionEntry structureSelectionEntry, StructureManager structureManager, RegistryAccess registryAccess, RandomState randomState, StructureTemplateManager structureTemplateManager, long l, ChunkAccess chunkAccess, ChunkPos chunkPos, SectionPos sectionPos, CallbackInfoReturnable<Boolean> callbackInfoReturnable, Structure structure, int n, HolderSet<Biome> holderSet, Predicate<Holder<Biome>> predicate, StructureStart structureStart) {
        void chunkPos2;
        void registryAccess2;
        void structure2;
        void manager;
        void structurestart;
        if (AsyncStructureSpawnEvent.getHandlerList().getRegisteredListeners().length == 0) {
            return;
        }
        BoundingBox box = structurestart.m_73601_();
        AsyncStructureSpawnEvent event = new AsyncStructureSpawnEvent(((WorldBridge)((IWorldBridge)manager.f_220460_).bridge$getMinecraftWorld()).bridge$getWorld(), CraftStructure.minecraftToBukkit((Structure)structure2, (RegistryAccess)registryAccess2), new org.bukkit.util.BoundingBox(box.m_162395_(), box.m_162396_(), box.m_162398_(), box.m_162399_(), box.m_162400_(), box.m_162401_()), chunkPos2.f_45578_, chunkPos2.f_45579_);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            void cir;
            cir.setReturnValue((Object)true);
        }
    }

    public void applyBiomeDecoration(WorldGenLevel level, ChunkAccess chunkAccess, StructureManager structureFeatureManager, boolean vanilla) {
        if (vanilla) {
            this.m_213609_(level, chunkAccess, structureFeatureManager);
        } else {
            this.addDecorations(level, chunkAccess, structureFeatureManager);
        }
    }

    private void addDecorations(WorldGenLevel region, ChunkAccess chunk, StructureManager structureManager) {
        CraftWorld world = ((WorldBridge)((IWorldBridge)region).bridge$getMinecraftWorld()).bridge$getWorld();
        if (!world.getPopulators().isEmpty()) {
            CraftLimitedRegion limitedRegion = new CraftLimitedRegion(region, chunk.m_7697_());
            int x = chunk.m_7697_().f_45578_;
            int z = chunk.m_7697_().f_45579_;
            for (BlockPopulator populator : world.getPopulators()) {
                WorldgenRandom random = new WorldgenRandom((RandomSource)new LegacyRandomSource(region.m_7328_()));
                random.m_64690_(region.m_7328_(), x, z);
                populator.populate(world, new RandomSourceWrapper.RandomWrapper((RandomSource)random), x, z, limitedRegion);
            }
            limitedRegion.saveEntities();
            limitedRegion.breakLink();
        }
    }

    @Override
    public void bridge$setBiomeSource(BiomeSource biomeSource) {
        this.f_62137_ = biomeSource;
    }
}

