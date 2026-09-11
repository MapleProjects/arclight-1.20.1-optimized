/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.mojang.serialization.Codec
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.BiomeSource
 *  net.minecraft.world.level.biome.Climate$Sampler
 */
package org.bukkit.craftbukkit.v1_20_R1.generator;

import com.google.common.base.Preconditions;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.generator.CraftBiomeParameterPoint;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

public class CustomWorldChunkManager
extends BiomeSource {
    private final WorldInfo worldInfo;
    private final BiomeProvider biomeProvider;
    private final Registry<net.minecraft.world.level.biome.Biome> registry;

    private static List<Holder<net.minecraft.world.level.biome.Biome>> biomeListToBiomeBaseList(List<Biome> biomes, Registry<net.minecraft.world.level.biome.Biome> registry) {
        ArrayList<Holder<net.minecraft.world.level.biome.Biome>> biomeBases = new ArrayList<Holder<net.minecraft.world.level.biome.Biome>>();
        for (Biome biome : biomes) {
            Preconditions.checkArgument((biome != Biome.CUSTOM ? 1 : 0) != 0, (String)"Cannot use the biome %s", (Object)biome);
            biomeBases.add(CraftBlock.biomeToBiomeBase(registry, biome));
        }
        return biomeBases;
    }

    public CustomWorldChunkManager(WorldInfo worldInfo, BiomeProvider biomeProvider, Registry<net.minecraft.world.level.biome.Biome> registry) {
        this.worldInfo = worldInfo;
        this.biomeProvider = biomeProvider;
        this.registry = registry;
    }

    protected Codec<? extends BiomeSource> m_5820_() {
        throw new UnsupportedOperationException("Cannot serialize CustomWorldChunkManager");
    }

    public Holder<net.minecraft.world.level.biome.Biome> m_203407_(int x, int y, int z, Climate.Sampler sampler) {
        Biome biome = this.biomeProvider.getBiome(this.worldInfo, x << 2, y << 2, z << 2, CraftBiomeParameterPoint.createBiomeParameterPoint(sampler, sampler.m_183445_(x, y, z)));
        Preconditions.checkArgument((biome != Biome.CUSTOM ? 1 : 0) != 0, (String)"Cannot set the biome to %s", (Object)biome);
        return CraftBlock.biomeToBiomeBase(this.registry, biome);
    }

    protected Stream<Holder<net.minecraft.world.level.biome.Biome>> m_274359_() {
        return CustomWorldChunkManager.biomeListToBiomeBaseList(this.biomeProvider.getBiomes(this.worldInfo), this.registry).stream();
    }
}

