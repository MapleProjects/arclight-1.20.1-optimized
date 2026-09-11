/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util.noise;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.OctaveGenerator;
import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.jetbrains.annotations.NotNull;

public class PerlinOctaveGenerator
extends OctaveGenerator {
    public PerlinOctaveGenerator(@NotNull World world, int octaves) {
        this(new Random(world.getSeed()), octaves);
    }

    public PerlinOctaveGenerator(long seed, int octaves) {
        this(new Random(seed), octaves);
    }

    public PerlinOctaveGenerator(@NotNull Random rand, int octaves) {
        super(PerlinOctaveGenerator.createOctaves(rand, octaves));
    }

    @NotNull
    private static NoiseGenerator[] createOctaves(@NotNull Random rand, int octaves) {
        NoiseGenerator[] result = new NoiseGenerator[octaves];
        int i = 0;
        while (i < octaves) {
            result[i] = new PerlinNoiseGenerator(rand);
            ++i;
        }
        return result;
    }
}

