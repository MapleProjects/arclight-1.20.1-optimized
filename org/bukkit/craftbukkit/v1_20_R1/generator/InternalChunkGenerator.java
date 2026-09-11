/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeGenerationSettings
 *  net.minecraft.world.level.biome.BiomeSource
 *  net.minecraft.world.level.chunk.ChunkGenerator
 */
package org.bukkit.craftbukkit.v1_20_R1.generator;

import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;

public abstract class InternalChunkGenerator
extends ChunkGenerator {
    public InternalChunkGenerator(BiomeSource worldchunkmanager, Function<Holder<Biome>, BiomeGenerationSettings> function) {
        super(worldchunkmanager, function);
    }
}

