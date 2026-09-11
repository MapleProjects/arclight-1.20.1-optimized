/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.chunk.storage.ChunkStorage
 *  net.minecraft.world.level.dimension.LevelStem
 *  net.minecraft.world.level.levelgen.structure.LegacyStructureDataHandler
 *  net.minecraft.world.level.storage.DimensionDataStorage
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.chunk.storage;

import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.storage.ChunkStorage;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.structure.LegacyStructureDataHandler;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ChunkStorage.class})
public abstract class ChunkLoaderMixin {
    @Redirect(method={"getLegacyStructureHandler"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/levelgen/structure/LegacyStructureDataHandler;getLegacyStructureHandler(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/storage/DimensionDataStorage;)Lnet/minecraft/world/level/levelgen/structure/LegacyStructureDataHandler;"))
    private LegacyStructureDataHandler arclight$legacyData(ResourceKey<Level> p_236992_0_, DimensionDataStorage p_236992_1_) {
        return ChunkLoaderMixin.legacyDataOf(p_236992_0_, p_236992_1_);
    }

    private static LegacyStructureDataHandler legacyDataOf(ResourceKey<?> typeKey, @Nullable DimensionDataStorage dataManager) {
        if (typeKey == LevelStem.f_63971_ || typeKey == Level.f_46428_) {
            return new LegacyStructureDataHandler(dataManager, (List)ImmutableList.of((Object)"Monument", (Object)"Stronghold", (Object)"Village", (Object)"Mineshaft", (Object)"Temple", (Object)"Mansion"), (List)ImmutableList.of((Object)"Village", (Object)"Mineshaft", (Object)"Mansion", (Object)"Igloo", (Object)"Desert_Pyramid", (Object)"Jungle_Pyramid", (Object)"Swamp_Hut", (Object)"Stronghold", (Object)"Monument"));
        }
        if (typeKey == LevelStem.f_63972_ || typeKey == Level.f_46429_) {
            ImmutableList list1 = ImmutableList.of((Object)"Fortress");
            return new LegacyStructureDataHandler(dataManager, (List)list1, (List)list1);
        }
        if (typeKey == LevelStem.f_63973_ || typeKey == Level.f_46430_) {
            ImmutableList list = ImmutableList.of((Object)"EndCity");
            return new LegacyStructureDataHandler(dataManager, (List)list, (List)list);
        }
        throw new RuntimeException(String.format("Unknown dimension type : %s", typeKey));
    }
}

