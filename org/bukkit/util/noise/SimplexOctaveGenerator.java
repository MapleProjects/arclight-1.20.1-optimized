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
import org.bukkit.util.noise.SimplexNoiseGenerator;
import org.jetbrains.annotations.NotNull;

public class SimplexOctaveGenerator
extends OctaveGenerator {
    private double wScale = 1.0;

    public SimplexOctaveGenerator(@NotNull World world, int octaves) {
        this(new Random(world.getSeed()), octaves);
    }

    public SimplexOctaveGenerator(long seed, int octaves) {
        this(new Random(seed), octaves);
    }

    public SimplexOctaveGenerator(@NotNull Random rand, int octaves) {
        super(SimplexOctaveGenerator.createOctaves(rand, octaves));
    }

    @Override
    public void setScale(double scale) {
        super.setScale(scale);
        this.setWScale(scale);
    }

    public double getWScale() {
        return this.wScale;
    }

    public void setWScale(double scale) {
        this.wScale = scale;
    }

    public double noise(double x, double y, double z, double w, double frequency, double amplitude) {
        return this.noise(x, y, z, w, frequency, amplitude, false);
    }

    public double noise(double x, double y, double z, double w, double frequency, double amplitude, boolean normalized) {
        double result = 0.0;
        double amp = 1.0;
        double freq = 1.0;
        double max = 0.0;
        x *= this.xScale;
        y *= this.yScale;
        z *= this.zScale;
        w *= this.wScale;
        NoiseGenerator[] noiseGeneratorArray = this.octaves;
        int n = this.octaves.length;
        int n2 = 0;
        while (n2 < n) {
            NoiseGenerator octave = noiseGeneratorArray[n2];
            result += ((SimplexNoiseGenerator)octave).noise(x * freq, y * freq, z * freq, w * freq) * amp;
            max += amp;
            freq *= frequency;
            amp *= amplitude;
            ++n2;
        }
        if (normalized) {
            result /= max;
        }
        return result;
    }

    @NotNull
    private static NoiseGenerator[] createOctaves(@NotNull Random rand, int octaves) {
        NoiseGenerator[] result = new NoiseGenerator[octaves];
        int i = 0;
        while (i < octaves) {
            result[i] = new SimplexNoiseGenerator(rand);
            ++i;
        }
        return result;
    }
}

