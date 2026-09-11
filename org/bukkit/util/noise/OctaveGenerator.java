/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util.noise;

import org.bukkit.util.noise.NoiseGenerator;
import org.jetbrains.annotations.NotNull;

public abstract class OctaveGenerator {
    @NotNull
    protected final NoiseGenerator[] octaves;
    protected double xScale = 1.0;
    protected double yScale = 1.0;
    protected double zScale = 1.0;

    protected OctaveGenerator(@NotNull NoiseGenerator[] octaves) {
        this.octaves = octaves;
    }

    public void setScale(double scale) {
        this.setXScale(scale);
        this.setYScale(scale);
        this.setZScale(scale);
    }

    public double getXScale() {
        return this.xScale;
    }

    public void setXScale(double scale) {
        this.xScale = scale;
    }

    public double getYScale() {
        return this.yScale;
    }

    public void setYScale(double scale) {
        this.yScale = scale;
    }

    public double getZScale() {
        return this.zScale;
    }

    public void setZScale(double scale) {
        this.zScale = scale;
    }

    @NotNull
    public NoiseGenerator[] getOctaves() {
        return (NoiseGenerator[])this.octaves.clone();
    }

    public double noise(double x, double frequency, double amplitude) {
        return this.noise(x, 0.0, 0.0, frequency, amplitude);
    }

    public double noise(double x, double frequency, double amplitude, boolean normalized) {
        return this.noise(x, 0.0, 0.0, frequency, amplitude, normalized);
    }

    public double noise(double x, double y, double frequency, double amplitude) {
        return this.noise(x, y, 0.0, frequency, amplitude);
    }

    public double noise(double x, double y, double frequency, double amplitude, boolean normalized) {
        return this.noise(x, y, 0.0, frequency, amplitude, normalized);
    }

    public double noise(double x, double y, double z, double frequency, double amplitude) {
        return this.noise(x, y, z, frequency, amplitude, false);
    }

    public double noise(double x, double y, double z, double frequency, double amplitude, boolean normalized) {
        double result = 0.0;
        double amp = 1.0;
        double freq = 1.0;
        double max = 0.0;
        x *= this.xScale;
        y *= this.yScale;
        z *= this.zScale;
        NoiseGenerator[] noiseGeneratorArray = this.octaves;
        int n = this.octaves.length;
        int n2 = 0;
        while (n2 < n) {
            NoiseGenerator octave = noiseGeneratorArray[n2];
            result += octave.noise(x * freq, y * freq, z * freq) * amp;
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
}

