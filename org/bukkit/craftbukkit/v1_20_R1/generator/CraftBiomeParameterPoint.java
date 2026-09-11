/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.biome.Climate
 *  net.minecraft.world.level.biome.Climate$Sampler
 *  net.minecraft.world.level.biome.Climate$TargetPoint
 */
package org.bukkit.craftbukkit.v1_20_R1.generator;

import net.minecraft.world.level.biome.Climate;
import org.bukkit.generator.BiomeParameterPoint;

public class CraftBiomeParameterPoint
implements BiomeParameterPoint {
    private final double temperature;
    private final double humidity;
    private final double continentalness;
    private final double erosion;
    private final double depth;
    private final double weirdness;
    private final Climate.Sampler sampler;

    public static BiomeParameterPoint createBiomeParameterPoint(Climate.Sampler sampler, Climate.TargetPoint targetPoint) {
        return new CraftBiomeParameterPoint(sampler, Climate.m_186796_((long)targetPoint.f_187003_()), Climate.m_186796_((long)targetPoint.f_187004_()), Climate.m_186796_((long)targetPoint.f_187005_()), Climate.m_186796_((long)targetPoint.f_187006_()), Climate.m_186796_((long)targetPoint.f_187007_()), Climate.m_186796_((long)targetPoint.f_187008_()));
    }

    private CraftBiomeParameterPoint(Climate.Sampler sampler, double temperature, double humidity, double continentalness, double erosion, double depth, double weirdness) {
        this.sampler = sampler;
        this.temperature = temperature;
        this.humidity = humidity;
        this.continentalness = continentalness;
        this.erosion = erosion;
        this.depth = depth;
        this.weirdness = weirdness;
    }

    @Override
    public double getTemperature() {
        return this.temperature;
    }

    @Override
    public double getMaxTemperature() {
        return this.sampler.f_207845_().m_207401_();
    }

    @Override
    public double getMinTemperature() {
        return this.sampler.f_207845_().m_207402_();
    }

    @Override
    public double getHumidity() {
        return this.humidity;
    }

    @Override
    public double getMaxHumidity() {
        return this.sampler.f_207846_().m_207401_();
    }

    @Override
    public double getMinHumidity() {
        return this.sampler.f_207846_().m_207402_();
    }

    @Override
    public double getContinentalness() {
        return this.continentalness;
    }

    @Override
    public double getMaxContinentalness() {
        return this.sampler.f_207847_().m_207401_();
    }

    @Override
    public double getMinContinentalness() {
        return this.sampler.f_207847_().m_207402_();
    }

    @Override
    public double getErosion() {
        return this.erosion;
    }

    @Override
    public double getMaxErosion() {
        return this.sampler.f_207848_().m_207401_();
    }

    @Override
    public double getMinErosion() {
        return this.sampler.f_207848_().m_207402_();
    }

    @Override
    public double getDepth() {
        return this.depth;
    }

    @Override
    public double getMaxDepth() {
        return this.sampler.f_207849_().m_207401_();
    }

    @Override
    public double getMinDepth() {
        return this.sampler.f_207849_().m_207402_();
    }

    @Override
    public double getWeirdness() {
        return this.weirdness;
    }

    @Override
    public double getMaxWeirdness() {
        return this.sampler.f_207850_().m_207401_();
    }

    @Override
    public double getMinWeirdness() {
        return this.sampler.f_207850_().m_207402_();
    }
}

